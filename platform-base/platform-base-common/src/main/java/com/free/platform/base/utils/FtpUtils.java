package com.free.platform.base.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ftp文件操作
 */
public class FtpUtils {

    /**
     * 日志对象
     */
    private static final Logger log = LoggerFactory.getLogger(FtpUtils.class);

    /**
     * FTP服务器主机IP地址
     */
    private String ftpHostIP;

    /**
     * FTP服务器主机端口
     */
    private String ftpHostPort;

    /**
     * FTP服务器登录用户名称
     */
    private String ftpUsername;

    /**
     * FTP服务器登录用户密码
     */
    private String ftpPassword;

    /**
     * 传输控制字符集编码
     */
    private static final String CONTROL_ENCODING = "UTF-8";

    /**
     * FTP客户端对象
     */
    private FTPClient ftpClient = new FTPClient();

    public FtpUtils(String ftpHostIP, String ftpHostPort, String ftpUsername, String ftpPassword) {
        this.ftpHostIP = ftpHostIP;
        this.ftpHostPort = ftpHostPort;
        this.ftpUsername = ftpUsername;
        this.ftpPassword = ftpPassword;
    }

    /**
     * 连接FTP服务器
     * 
     * @param hostIp
     *            FTP服务器主机IP地址
     * @param hostPort
     *            FTP服务器主机端口
     * @param username
     *            FTP服务器登录用户名称
     * @param password
     *            FTP服务器登录密码
     * @return true-连接登录成功 false-连接登录失败
     */
    public Boolean connect(String hostIp, String hostPort, String username, String password) {
        try {
            ftpClient.connect(hostIp, Integer.valueOf(hostPort));
            if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                log.info(">>>>>>>>FTP服务器连接成功!");
                if (ftpClient.login(username, password)) {
                    log.info(">>>>>>>>FTP服务器登陆成功!");
                    return true;
                } else {
                    log.info(">>>>>>>>FTP服务器登陆用户名或密码错误，登录失败!");
                }
            } else {
                log.info(">>>>>>>>FTP服务器连接失败!");
            }
        } catch (IOException e) {
            log.error(">>>>>>>>FTP服务器连接失败!", e);
        }
        try {
            ftpClient.disconnect();
        } catch (IOException e) {
            log.error(">>>>>>>>FTP服务器关闭连接失败!", e);
        }
        return false;
    }

    /**
     * 关闭FTP连接
     */
    public void disconnect() {
        try {
            if (ftpClient.isConnected())
                ftpClient.disconnect();
        } catch (IOException e) {
            log.error(">>>>>>>>FTP服务器关闭连接失败!", e);
        }
    }

    /**
     * 删除远程FTP文件
     * 
     * @param remote
     *            远程文件路径
     * @return FTP文件操作状态
     * @throws IOException
     */
    public FTPStatus delete(String remote) {
        FTPStatus result = null;
        try {
            if (connect(ftpHostIP, ftpHostPort, ftpUsername, ftpPassword)) {
                ftpClient.enterLocalPassiveMode();
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
                ftpClient.setControlEncoding(CONTROL_ENCODING);
                FTPFile[] files = ftpClient.listFiles(remote);
                if (files.length == 1) {
                    boolean status = ftpClient.deleteFile(remote);
                    result = status ? FTPStatus.Delete_Remote_Success : FTPStatus.Delete_Remote_Faild;
                } else {
                    result = FTPStatus.Not_Exist_File;
                }
                log.info("FTP服务器文件删除标识：" + result);
            }
        } catch (IOException e) {
            log.error(">>>>>>>>FTP服务器删除文件失败!", e);
        } finally {
            disconnect();
        }
        return result;
    }

    /**
     * 重命名远程FTP文件
     * 
     * @param from
     *            原来远程文件路径
     * @param to
     *            新远程文件名称(路径-必须保证在同一路径下)
     * @return 是否成功
     */
    public FTPStatus rename(String from, String to) {
        FTPStatus result = null;
        try {
            if (connect(ftpHostIP, ftpHostPort, ftpUsername, ftpPassword)) {
                ftpClient.enterLocalPassiveMode();
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
                ftpClient.setControlEncoding(CONTROL_ENCODING);
                FTPFile[] files = ftpClient.listFiles(from);
                if (files.length == 1) {
                    boolean status = ftpClient.rename(from, to);
                    result = status ? FTPStatus.Remote_Rename_Success : FTPStatus.Remote_Rename_Faild;
                } else {
                    result = FTPStatus.Not_Exist_File;
                }
                log.info("FTP服务器文件名更新标识：" + result);
            }
        } catch (IOException e) {
            log.error(">>>>>>>>FTP服务器文件重命名失败!", e);
        } finally {
            disconnect();
        }
        return result;
    }

    /**
     * 从FTP服务器上下载单个文件
     * 
     * @param remote
     *            远程文件路径
     * @param local
     *            本地文件路径
     * @return 是否成功
     */

    public FTPStatus singleDownload(String remote, String local) throws IOException {
        FTPStatus result = null;
        try {
            if (connect(ftpHostIP, ftpHostPort, ftpUsername, ftpPassword)) {
                ftpClient.enterLocalPassiveMode();
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                FTPFile[] files = ftpClient.listFiles(remote);
                if (files.length == 0) {
                    log.info("FTP服务器文件不存在");
                    return FTPStatus.Not_Exist_File;
                } else if (files.length > 1) {
                    log.info("FTP服务器文件有多个");
                    return FTPStatus.File_Not_Unique;
                } else {
                    File localFile = new File(local);
                    if (localFile.exists()) {
                        log.info("本地文件大小为:" + localFile.length());
                        OutputStream out = new FileOutputStream(localFile, true);
                        if (localFile.length() >= files[0].getSize()) {
                            log.info("本地文件大小超过远程文件大小，下载中止");
                            return FTPStatus.Remote_smaller_local;
                        }
                        ftpClient.setRestartOffset(localFile.length());
                        boolean status = ftpClient.retrieveFile(remote, out);
                        result = status ? FTPStatus.Download_From_Break_Success : FTPStatus.Download_From_Break_Faild;
                        out.close();
                    } else {
                        OutputStream out = new FileOutputStream(localFile);
                        boolean status = ftpClient.retrieveFile(remote, out);
                        result = status ? FTPStatus.Download_From_Break_Success : FTPStatus.Download_From_Break_Faild;
                        out.close();
                    }
                }
            }
        } catch (IOException e) {
            log.error(">>>>>>>>FTP服务器文件下载失败!", e);
        } finally {
            disconnect();
        }
        return result;
    }

    /**
     * 从FTP服务器上下载文件
     * 
     * @param remote
     *            远程文件目录
     * @param local
     *            本地文件目录
     * @return true-下载成功，false-下载失败
     */
    public Boolean download(String remotePath, String localPath) throws IOException {
        try {
            if (connect(ftpHostIP, ftpHostPort, ftpUsername, ftpPassword)) {
                ftpClient.enterLocalPassiveMode();
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
                ftpClient.setControlEncoding(CONTROL_ENCODING);
                Boolean success = ftpClient.changeWorkingDirectory(remotePath);
                if (success) {
                    FTPFile[] files = ftpClient.listFiles();
                    if (files.length > 0) {
                        File localDirect = new File(localPath);
                        for (FTPFile ftpFile : files) {
                            String remoteFileName = ftpFile.getName();
                            String[] fileNames = localDirect.list();
                            if (fileNames.length > 0) {
                                if (!isInArray(remoteFileName, fileNames)) {
                                    File localFile = new File(localPath + remoteFileName);
                                    OutputStream outputStream = new FileOutputStream(localFile);
                                    ftpClient.retrieveFile(remoteFileName, outputStream);
                                    outputStream.close();
                                }
                            } else {
                                File localFile = new File(localPath + remoteFileName);
                                OutputStream outputStream = new FileOutputStream(localFile);
                                ftpClient.retrieveFile(remoteFileName, outputStream);
                                outputStream.close();
                            }
                        }
                        return true;
                    } else {
                        log.info(">>>>>>>>FTP服务器上" + remotePath + "目录没有文件");
                    }
                } else {
                    log.info(">>>>>>>>FTP服务器上" + remotePath + "目录不存在");
                }
            }
        } catch (Exception e) {
            log.error(">>>>>>>>FTP服务器文件下载失败!", e);
        } finally {
            disconnect();
        }
        return false;
    }

    /**
     * 上传文件到FTP服务器
     * 
     * @param local
     *            本地文件名称，绝对路径
     * @param remote
     *            远程文件路径，使用/home/directory1/subdirectory/file.ext
     *            按照Linux上的路径指定方式，支持多级目录嵌套，支持递归创建不存在的目录结构
     * @return 上传结果
     */
    public FTPStatus upload(String local, String remote) {
        FTPStatus result = null;
        try {
            if (connect(ftpHostIP, ftpHostPort, ftpUsername, ftpPassword)) {
                ftpClient.enterLocalPassiveMode();
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                // 对远程目录的处理
                String remoteFileName = remote;
                if (remote.contains(File.separator)) {
                    remoteFileName = remote.substring(remote.lastIndexOf(File.separator) + 1);
                    String directory = remote.substring(0, remote.lastIndexOf(File.separator) + 1);
                    if (!directory.equalsIgnoreCase(File.separator) && !ftpClient.changeWorkingDirectory(directory)) {
                        // 如果远程目录不存在，则递归创建远程服务器目录
                        int start = 0;
                        int end = 0;
                        if (directory.startsWith(File.separator)) {
                            start = 1;
                        } else {
                            start = 0;
                        }
                        end = directory.indexOf(File.separator, start);
                        while (true) {
                            String subDirectory = remote.substring(start, end);
                            if (!ftpClient.changeWorkingDirectory(subDirectory)) {
                                if (ftpClient.makeDirectory(subDirectory)) {
                                    ftpClient.changeWorkingDirectory(subDirectory);
                                } else {
                                    log.info("创建目录失败");
                                    return FTPStatus.Create_Directory_Fail;
                                }
                            }
                            start = end + 1;
                            end = directory.indexOf(File.separator, start);
                            // 检查所有目录是否创建完毕
                            if (end <= start) {
                                break;
                            }
                        }
                    }
                }
                // 检查远程是否存在文件
                InputStream is = new FileInputStream(local);
                if (ftpClient.storeFile(remoteFileName, is)) {
                    result = FTPStatus.Upload_New_File_Success;
                } else {
                    result = FTPStatus.Upload_New_File_Failed;
                }
                is.close();
            }
        } catch (IOException e) {
            log.error(">>>>>>>>FTP服务器文件上传失败!", e);
        } finally {
            disconnect();
        }
        return result;
    }

    /**
     * 判断传入的参数是否是数组的元素
     * 
     * @param param
     *            传入参数
     * @param value
     *            数组
     */
    public static boolean isInArray(String param, String[] value) {
        if (param == null)
            return false;
        if (value == null)
            return false;
        for (int i = 0; i < value.length; i++) {
            if (param.equals(value[i])) {
                return true;
            }
        }
        return false;
    }
}
