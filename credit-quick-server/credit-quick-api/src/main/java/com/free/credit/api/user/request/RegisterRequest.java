package com.free.credit.api.user.request;


import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;
import com.free.credit.api.common.annotation.XnParam;
import com.free.platform.base.common.BaseEntity;

public class RegisterRequest extends BaseEntity {
    private static final long serialVersionUID = 1922489511473510087L;

    /**
     * 用户手机号
     */
    @NotBlank(message = "手机号码不能为空")
    @Pattern(regexp = "^1\\d{10}$", message = "请输入正确的手机号")
    private String mobile;
    
    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空")
    private String vCode;
      
    /**
     * 用户密码
     */
    @Pattern(regexp = "^(?![0-9]+$)(?![A-Z]+$)(?![a-z]+$)[\\d\\D]{6,16}$", message = "密码格式错误")
    @XnParam(isShowInLog=false)
    private String password;
    
    /**
     *推荐码
     */
    private String refereeCode;
    
    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the vCode
     */
    public String getvCode() {
        return vCode;
    }

    /**
     * @param vCode the vCode to set
     */
    public void setvCode(String vCode) {
        this.vCode = vCode;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the refereeCode
     */
    public String getRefereeCode() {
        return refereeCode;
    }

    /**
     * @param refereeCode the refereeCode to set
     */
    public void setRefereeCode(String refereeCode) {
        this.refereeCode = refereeCode;
    }

}
