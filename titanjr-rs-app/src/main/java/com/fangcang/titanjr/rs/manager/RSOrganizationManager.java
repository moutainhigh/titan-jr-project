package com.fangcang.titanjr.rs.manager;

import com.fangcang.titanjr.rs.request.*;
import com.fangcang.titanjr.rs.response.*;

/**
 * Created by zhaoshan on 2016/4/8.
 * 包含机构类账户注册、更新；
 * 个人类账户注册、更新；
 * 机构状态查询，机构信息查询 6个接口
 */
public interface RSOrganizationManager {

    /**
     * 在融数注册机构类账户
     * @param companyOrgRegRequest
     * @return
     */
    public CompanyOrgRegResponse resigterCompanyOrg(CompanyOrgRegRequest companyOrgRegRequest);

    /**
     * 融数修改机构类账户
     * @param companyOrgUpdateRequest
     * @return
     */
    public CompanyOrgUpdateResponse updateCompanyOrg(CompanyOrgUpdateRequest companyOrgUpdateRequest);

    /**
     * 在融数注册个人类账户
     * @param personOrgRegRequest
     * @return
     */
    public PersonOrgRegResponse resigterPersonOrg(PersonOrgRegRequest personOrgRegRequest);

    /**
     * 融数修改个人类账户
     * @param personOrgUpdateRequest
     * @return
     */
    public PersonOrgUpdateResponse updatePersonOrg(PersonOrgUpdateRequest personOrgUpdateRequest);

    /**
     * 查询机构状态
     * 账户状态（1：生效，2：冻结，3：注销） 不填默认为查询 1：生效状态
     * @param orgStatusQueryRequest
     * @return
     */
    public OrgStatusQueryResponse queryOrgStatus(OrgStatusQueryRequest orgStatusQueryRequest);


    /**
     * 查询已注册的企业机构的信息
     * @param compOrgInfoQueryRequest
     * @return
     */
    public CompOrgInfoQueryResponse queryCompOrgInfo(CompOrgInfoQueryRequest compOrgInfoQueryRequest);

    /**
     * 查询已注册的个人机构的信息
     * @param persOrgInfoQueryRequest
     * @return
     */
    public PersOrgInfoQueryResponse queryPersOrgInfo(PersOrgInfoQueryRequest persOrgInfoQueryRequest);
}
