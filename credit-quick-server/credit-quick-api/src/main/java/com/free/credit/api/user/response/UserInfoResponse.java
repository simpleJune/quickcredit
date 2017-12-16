package com.free.credit.api.user.response;


import com.free.platform.base.common.BaseEntity;

public class UserInfoResponse extends BaseEntity {
    private static final long serialVersionUID = -5879187271933231351L;

    /**
     * 认证步骤
     */
    private String authStep;

    /**
     * 认证步骤: 0/未完成, 1/已完成
     */
    private String authStepStatus = "0";

    /**
     * 审核状态
     */
    private String approvalStatus;

    /**
     * 借款单状态
     */
    private String loanOrderStatus;

    /**
     * 可借额度
     */
    private Long loanLimit;

    /**
     * 审核额度有效期
     */
    private String validityDate;

    /**
     * 审批额度过期状态
     */
    private String expiryStatus;

    /**
     * 当前应还金额
     */
    private String currentReturnBackAmt;

    /**
     * 到期还款日
     */
    private String refundDate;

    /**
     * 剩余审核次数
     */
    private Integer approvalTimes;

    /**
     * 剩余借款次数
     */
    private Integer borrowTimes;

    public String getAuthStep() {
        return authStep;
    }

    public void setAuthStep(String authStep) {
        this.authStep = authStep;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getLoanOrderStatus() {
        return loanOrderStatus;
    }

    public void setLoanOrderStatus(String loanOrderStatus) {
        this.loanOrderStatus = loanOrderStatus;
    }

    public Long getLoanLimit() {
        return loanLimit;
    }

    public void setLoanLimit(Long loanLimit) {
        this.loanLimit = loanLimit;
    }

    public String getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(String validityDate) {
        this.validityDate = validityDate;
    }

    public String getExpiryStatus() {
        return expiryStatus;
    }

    public void setExpiryStatus(String expiryStatus) {
        this.expiryStatus = expiryStatus;
    }

    public String getCurrentReturnBackAmt() {
        return currentReturnBackAmt;
    }

    public void setCurrentReturnBackAmt(String currentReturnBackAmt) {
        this.currentReturnBackAmt = currentReturnBackAmt;
    }

    public String getRefundDate() {
        return refundDate;
    }

    public void setRefundDate(String refundDate) {
        this.refundDate = refundDate;
    }

    public String getAuthStepStatus() {
        return authStepStatus;
    }

    public void setAuthStepStatus(String authStepStatus) {
        this.authStepStatus = authStepStatus;
    }

    public Integer getApprovalTimes() {
        return approvalTimes;
    }

    public void setApprovalTimes(Integer approvalTimes) {
        this.approvalTimes = approvalTimes;
    }

    public Integer getBorrowTimes() {
        return borrowTimes;
    }

    public void setBorrowTimes(Integer borrowTimes) {
        this.borrowTimes = borrowTimes;
    }
}
