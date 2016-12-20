<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

    <div class="loanBox">
    
    	<input type="hidden" id="accessoryType" field="assureType" value=""/>
    	
    	<div fieldObj="creditCompany" style="display:none">
	    	<!--  目标企业 -->
	    	<input type="hidden" field="licenseUrl" fileId="licenseUrl" typeId="business_license" name="licenseUrl" value=""/>
	    	<input type="hidden" field="orgCodeUrl" fileId="orgCodeUrl" typeId="organization_code" name="orgCodeUrl" value=""/>
	    	<input type="hidden" field="taxRegUrl" fileId="taxRegUrl"  typeId="tax_registration_certificate" name="taxRegUrl" value=""/>
	    	<input type="hidden" field="accountUrl" fileId="accountUrl" typeId="account_opening_license" name="accountUrl" value=""/>
	    	<input type="hidden" field="legalNoUrl" fileId="legalnoUrl" typeId="idCardUrl" name="legalnoUrl" value=""/>
	    	<input type="hidden" field="creditUrl" fileId="creditUrl"  typeId="credit_report"  name="creditUrl" value=""/>
	    	<input type="hidden" field="waterUrl" fileId="waterUrl"   typeId="bank_statements"   name="waterUrl" value=""/>
	    	<input type="hidden" field="officeNoUrl" fileId="officeNoUrl" typeId="proof_of_business_premises"  name="officeNoUrl" value=""/>
	    	<input type="hidden" field="officeUrl" fileId="officeUrl"  typeId="business_location_photos"  name="officeUrl" value=""/>
    	</div>
    	<div fieldObj="loanPersonEnsure" style="display:none">
	    	<!-- 个人担保 -->
	    	<input type="hidden" ensureType="1" field="idCardUrl" fileId="idCardUrl" typeId="guarantor/idcard"  name="idCardUrl" value=""/>
	    	<input type="hidden" ensureType="1" field="registeredUrl" fileId="registeredUrl" typeId="guarantor/family_register"  name="registeredUrl" value=""/>
	    	<input type="hidden" ensureType="1" field="spouseRegisteredUrl" fileId="spouseRegisteredUrl"  typeId="guarantor/spouse_account_book" name="spouseRegisteredUrl" value=""/>
	    	<input type="hidden" ensureType="1" field="spouseIdCardUrl" fileId="spouseIdCardUrl" typeId="guarantor/spouse_idcard"  name="spouseIdCardUrl" value=""/>
	    	<input type="hidden" ensureType="1" field="marriageUrl" fileId="marriageUrl"   typeId="guarantor/marriage_certificate" name="marriageUrl" value=""/>
    	</div>
    	
    	<div fieldObj="companyEnsure" style="display:none">
	    	<!-- 企业担保 -->
	    	<input type="hidden" ensureType="2" field="businessLicenseUrl" fileId="ensureBusinesslicenseUrl" typeId="guarantor/business_license"  name="ensureBusinesslicenseUrl" value=""/>
	    	<input type="hidden" ensureType="2" field="orgCodeCertificateUrl" fileId="ensureOrgCodeCertificateUrl" typeId="guarantor/organization_code"  name="ensureOrgCodeCertificateUrl" value=""/>
	    	<input type="hidden" ensureType="2" field="taxRegisterCodeUrl" fileId="ensureTaxRegisterCodeUrl"  typeId="guarantor/tax_registration_certificate" name="ensureTaxRegisterCodeUrl" value=""/>
	    	<input type="hidden" ensureType="2" field="licenseUrl" fileId="ensureLicenseUrl"  typeId="guarantor/account_opening_license" name="ensureLicenseUrl" value=""/>
	    	<input type="hidden" ensureType="2" field="legalPersonUrl" fileId="ensureLegalPersonUrl" typeId="guarantor/idcard"   name="ensureLegalPersonUrl" value=""/>
    	</div>
    	
    	
        <div class="loanStep">
            <span class="cur">1. 填写企业基本信息<i></i></span><span class="cur">2. 填写企业补充信息<i></i></span><span class="cur">3. 填写担保人信息<i></i></span><span class="cur">4. 上传资料<i></i></span><span>5. 提交授信申请</span>
        </div>
        <div class="loanInformation">
            <h3 class="loanInformation_title">企业证件资料</h3>
            <div class="loanInformation_upload">
                <dl class="loanInformation_upload_notice clearfix">
                    <dt class="fl">上传须知：</dt>
                    <dd class="fl">
                        <p>三证合一企业税务登记证和组织机构代码证请统一上传营业执照；</p>
                        <p>如一类资料下有多张图片，请上传ZIP或RAR文件，压缩文件大小不可超过20M，其他附件大小不超过5M：</p>
                        <p>附件格式支持 PNG、JPG 、 JPEG、PDF、ZIP、RAR；</p>
                    </dd>
                </dl>
                <ul class="loanInformation_upload_list clearfix">
                    <li>
                        <h3 class="loanInformation_upload_list_title"><i class="redNotice">*</i>企业营业执照</h3>
                        <div class="loanInformation_upload_list_img"> 
                            <div class="TFSaddImg"></div>
                            <input type="file"  name="credit_file" id="licenseUrl" errorMsg="请上传企业营业执照"/>
                            <div class="TFSuploading TFSupload_JPG hidden ">
                                 <p class="TFSuploading1">
                                   <span></span>
                                 </p>    
                                 <p class="TFSuploading2 "> 已上传<i>0</i>%  </p>                                
                            </div>
                            <div class="TFSuploaderror hidden">
                                <i class="J_re_upload loanInformation_upload_btn">重新上传</i>
                                <p>上传失败</p>
                            </div>
                            <div class="TFSimgOn hidden" >
                                <i class="J_delete_upload loanInformation_upload_btn">删除</i>
                                <img src="../images/TFS/help_logo.jpg">
                            </div>
                        </div>
                    </li>
                    <li>
                        <h3 class="loanInformation_upload_list_title"><i class="redNotice">*</i>组织机构代码证</h3>
                        <div class="loanInformation_upload_list_img">
                            <div class="TFSaddImg"></div>
                            <input type="file"  name="credit_file" id="orgCodeUrl" errorMsg="请上传组织机构代码证"/>
                            <div class="TFSuploading TFSupload_JPG hidden">
                                 <p class="TFSuploading1">
                                     <span></span>
                                 </p>    
                                 <p class="TFSuploading2"> 已上传<i>0</i>%
                                 </p>                                
                            </div>
                            <div class="TFSuploaderror hidden">
                                <i class="J_re_upload loanInformation_upload_btn">重新上传</i>
                                <p>上传失败</p>
                            </div>
                            <div class="TFSimgOn hidden">
                                <i class="J_delete_upload loanInformation_upload_btn">删除</i>
                                <img src="../images/TFS/help_logo.jpg">
                            </div>
                        </div>
                    </li>
                    <li>
                        <h3 class="loanInformation_upload_list_title"><i class="redNotice">*</i>税务登记证</h3>
                        <div class="loanInformation_upload_list_img">
                            <div class="TFSaddImg"></div>
                            <input type="file"  name="credit_file" id="taxRegUrl" errorMsg="请上传税务登记证"/>
                           <div class="TFSuploading TFSupload_JPG hidden">
                                 <p class="TFSuploading1">
                                     <span></span>
                                 </p>    
                                 <p class="TFSuploading2"> 已上传<i>0</i>%</p>                                
                            </div>
                            <div class="TFSuploaderror hidden">
                                <i class="J_re_upload loanInformation_upload_btn">重新上传</i>
                                <p>上传失败</p>
                            </div>
                            <div class="TFSimgOn hidden">
                                <i class="J_delete_upload loanInformation_upload_btn">删除</i>
                                <img src="../images/TFS/help_logo.jpg">
                            </div>
                        </div>
                    </li>
                    <li>
                        <h3 class="loanInformation_upload_list_title"><i class="redNotice">*</i>开户许可证</h3>
                        <div class="loanInformation_upload_list_img">
                            <div class="TFSaddImg"></div>
                              <input type="file"  name="credit_file" id="accountUrl"  errorMsg="请上传开户许可证"/>
                            <div class="TFSuploading TFSupload_JPG hidden">
                                 <p class="TFSuploading1">
                                     <span></span>
                                 </p>    
                                 <p class="TFSuploading2"> 已上传<i>0</i>%</p>                                
                            </div>
                            <div class="TFSuploaderror hidden">
                                <i class="J_re_upload loanInformation_upload_btn">重新上传</i>
                                <p>上传失败</p>
                            </div>
                            <div class="TFSimgOn hidden">
                                <i class="J_delete_upload loanInformation_upload_btn">删除</i>
                                <img src="../images/TFS/help_logo.jpg">
                            </div>
                        </div>
                    </li>
                    <li>
                        <h3 class="loanInformation_upload_list_title"><i class="redNotice">*</i>法人身份证</h3>
                        <div class="loanInformation_upload_list_img">
                            <div class="TFSaddImg"></div>
                             <input type="file"  name="credit_file" id="legalnoUrl"  errorMsg="请上传法人身份证"/>
                            <div class="TFSuploading TFSupload_JPG hidden">
                                 <p class="TFSuploading1">
                                     <span></span>
                                 </p>    
                                 <p class="TFSuploading2">已上传<i>0</i>% </p>                                
                            </div>
                            <div class="TFSuploaderror hidden">
                                <i class="J_re_upload loanInformation_upload_btn">重新上传</i>
                                <p>上传失败</p>
                            </div>
                            <div class="TFSimgOn hidden">
                                <i class="J_delete_upload loanInformation_upload_btn">删除</i>
                                <img src="../images/TFS/help_logo.jpg">
                            </div>
                        </div>
                    </li>
                    <li>
                        <h3 class="loanInformation_upload_list_title"><i class="redNotice">*</i>信用报告</h3>
                        <div class="loanInformation_upload_list_img">
                            <div class="TFSaddImg"></div>
                            <input type="file"  name="credit_file" id="creditUrl" errorMsg="请上传信用报告"/>
                            <div class="TFSuploading TFSupload_JPG hidden">
                                 <p class="TFSuploading1">
                                     <span></span>
                                 </p>    
                                 <p class="TFSuploading2">  已上传<i>0</i>% </p>                                
                            </div>
                            <div class="TFSuploaderror hidden">
                                <i class="J_re_upload loanInformation_upload_btn">重新上传</i>
                                <p>上传失败</p>
                            </div>
                            <div class="TFSimgOn hidden">
                                <i class="J_delete_upload loanInformation_upload_btn">删除</i>
                                <img src="../images/TFS/help_logo.jpg">
                            </div>
                        </div>
                    </li>
                    <li>
                        <h3 class="loanInformation_upload_list_title"><i class="redNotice">*</i>企业银行流水</h3>
                        <div class="loanInformation_upload_list_img">
                            <div class="TFSaddImg"></div>
                             <input type="file"  name="credit_file" id="waterUrl"  errorMsg="请上传企业银行流水"/>
                            <div class="TFSuploading TFSupload_JPG hidden">
                                 <p class="TFSuploading1">
                                     <span></span>
                                 </p>    
                                 <p class="TFSuploading2"> 已上传<i>0</i>% </p>                                
                            </div>
                            <div class="TFSuploaderror hidden">
                                <i class="J_re_upload loanInformation_upload_btn">重新上传</i>
                                <p>上传失败</p>
                            </div>
                            <div class="TFSimgOn hidden">
                                <i class="J_delete_upload loanInformation_upload_btn">删除</i>
                                <img src="../images/TFS/help_logo.jpg">
                            </div>
                        </div>
                    </li>
                    <li>
                        <h3 class="loanInformation_upload_list_title"><i class="redNotice">*</i>经营场所证明</h3>
                        <div class="loanInformation_upload_list_img">
                            <div class="TFSaddImg"></div>
                              <input type="file"  name="credit_file" id="officeNoUrl" errorMsg="请上传经营场所证明"/>
                            <div class="TFSuploading TFSupload_JPG hidden">
                                 <p class="TFSuploading1">
                                     <span></span>
                                 </p>    
                                 <p class="TFSuploading2"> 已上传<i>0</i>%</p>                                
                            </div>
                            <div class="TFSuploaderror hidden">
                                <i class="J_re_upload loanInformation_upload_btn">重新上传</i>
                                <p>上传失败</p>
                            </div>
                            <div class="TFSimgOn hidden">
                                <i class="J_delete_upload loanInformation_upload_btn">删除</i>
                                <img src="../images/TFS/help_logo.jpg">
                            </div>
                        </div>
                    </li>
                    <li>
                        <h3 class="loanInformation_upload_list_title"><i class="redNotice">*</i>经营场所照片</h3>
                        <div class="loanInformation_upload_list_img">
                            <div class="TFSaddImg"></div>
                              <input type="file"  name="credit_file" id="officeUrl" errorMsg="请上传经营场所照片"/>
                            <div class="TFSuploading TFSupload_JPG hidden">
                                 <p class="TFSuploading1">
                                     <span></span>
                                 </p>    
                                 <p class="TFSuploading2">已上传<i>0</i>%</p>                                
                            </div>
                            <div class="TFSuploaderror hidden">
                                <i class="J_re_upload loanInformation_upload_btn">重新上传</i>
                                <p>上传失败</p>
                            </div>
                            <div class="TFSimgOn hidden">
                                <i class="J_delete_upload loanInformation_upload_btn">删除</i>
                                <img src="../images/TFS/help_logo.jpg">
                            </div>
                        </div>
                    </li>
                </ul>
            </div>

            <h3 class="loanInformation_title">担保人证件资料</h3>
            <div class="loanInformation_upload">
                <dl class="loanInformation_upload_notice clearfix">
                    <dt class="fl">上传须知：</dt>
                    <dd class="fl">
                        <p>身份证正反面需在一张照片，户口本需上传主页和本人页；</p>
                        <p>文件格式支持 PDF、JPG 或 JPEG；</p>
                        <p>附件大小不超过5M；</p>
                    </dd>
                </dl>
                <ul class="loanInformation_upload_list clearfix">
                    <li fileId="idCardUrl">
                        <h3 class="loanInformation_upload_list_title">担保人身份证</h3>
                        <div class="loanInformation_upload_list_img">
                            <div class="TFSaddImg"></div>
                             <input type="file"  name="credit_file" id="idCardUrl" errorMsg="请上传担保人身份证"/>
                            <div class="TFSuploading TFSupload_JPG hidden">
                                 <p class="TFSuploading1">
                                     <span></span>
                                 </p>    
                                 <p class="TFSuploading2">已上传<i>0</i>%</p>                                
                            </div>
                            <div class="TFSuploaderror hidden">
                                <i class="J_re_upload loanInformation_upload_btn">重新上传</i>
                                <p>上传失败</p>
                            </div>
                            <div class="TFSimgOn hidden">
                                <i class="J_delete_upload loanInformation_upload_btn">删除</i>
                                <img src="../images/TFS/help_logo.jpg">
                            </div>
                        </div>
                    </li>
                    <li fileId="registeredUrl">
                        <h3 class="loanInformation_upload_list_title">担保人户口本</h3>
                        <div class="loanInformation_upload_list_img">
                            <div class="TFSaddImg"></div>
                               <input type="file"  name="credit_file" id="registeredUrl" errorMsg="请上传担保人户口本"/>
                            <div class="TFSuploading TFSupload_JPG hidden">
                                 <p class="TFSuploading1">
                                     <span></span>
                                 </p>    
                                 <p class="TFSuploading2"> 已上传<i>0</i>%</p>                                
                            </div>
                            <div class="TFSuploaderror hidden">
                                <i class="J_re_upload loanInformation_upload_btn">重新上传</i>
                                <p>上传失败</p>
                            </div>
                            <div class="TFSimgOn hidden">
                                <i class="J_delete_upload loanInformation_upload_btn">删除</i>
                                <img src="../images/TFS/help_logo.jpg">
                            </div>
                        </div>
                    </li>
                    <li fileId="spouseRegisteredUrl">
                        <h3 class="loanInformation_upload_list_title">配偶户口本</h3>
                        <div class="loanInformation_upload_list_img">
                            <div class="TFSaddImg"></div>
                             <input type="file"  name="credit_file" id="spouseRegisteredUrl"  errorMsg="请上传配偶户口本"/>
                           <div class="TFSuploading TFSupload_JPG hidden">
                                 <p class="TFSuploading1">
                                     <span></span>
                                 </p>    
                                 <p class="TFSuploading2"> 已上传<i>0</i>% </p>                                
                            </div>
                            <div class="TFSuploaderror hidden">
                                <i class="J_re_upload loanInformation_upload_btn">重新上传</i>
                                <p>上传失败</p>
                            </div>
                            <div class="TFSimgOn hidden">
                                <i class="J_delete_upload loanInformation_upload_btn">删除</i>
                                <img src="../images/TFS/help_logo.jpg">
                            </div>
                        </div>
                    </li>
                    <li fileId="spouseIdCardUrl">
                        <h3 class="loanInformation_upload_list_title">配偶身份证</h3>
                        <div class="loanInformation_upload_list_img">
                            <div class="TFSaddImg"></div>
                              <input type="file"  name="credit_file" id="spouseIdCardUrl"  errorMsg="请上传配偶身份证"/>
                            <div class="TFSuploading TFSupload_JPG hidden">
                                 <p class="TFSuploading1">
                                     <span></span>
                                 </p>    
                                 <p class="TFSuploading2"> 已上传<i>0</i>% </p>                                
                            </div>
                            <div class="TFSuploaderror hidden">
                                <i class="J_re_upload loanInformation_upload_btn">重新上传</i>
                                <p>上传失败</p>
                            </div>
                            <div class="TFSimgOn hidden">
                                <i class="J_delete_upload loanInformation_upload_btn">删除</i>
                                <img src="../images/TFS/help_logo.jpg">
                            </div>
                        </div>
                    </li>
                    <li fileId="marriageUrl">
                        <h3 class="loanInformation_upload_list_title">结婚证</h3>
                        <div class="loanInformation_upload_list_img">
                            <div class="TFSaddImg"></div>
                                <input type="file"  name="credit_file" id="marriageUrl" errorMsg="请上传结婚证"/>
                            <div class="TFSuploading TFSupload_JPG hidden">
                                 <p class="TFSuploading1">
                                     <span></span>
                                 </p>    
                                 <p class="TFSuploading2">  已上传<i>0</i>%
                                 </p>                                
                            </div>
                            <div class="TFSuploaderror hidden">
                                <i class="J_re_upload loanInformation_upload_btn">重新上传</i>
                                <p>上传失败</p>
                            </div>
                            <div class="TFSimgOn hidden">
                                <i class="J_delete_upload loanInformation_upload_btn">删除</i>
                                <img src="../images/TFS/help_logo.jpg">
                            </div>
                        </div>
                    </li>
                    <li  fileId="ensureBusinesslicenseUrl">
                        <h3 class="loanInformation_upload_list_title">企业营业执照</h3>
                        <div class="loanInformation_upload_list_img">
                            <div class="TFSaddImg"></div>
                            
                              <input type="file"  name="credit_file" id="ensureBusinesslicenseUrl" errorMsg="请上传企业营业执照"/>
                            <div class="TFSuploading TFSupload_JPG hidden">
                                 <p class="TFSuploading1">
                                     <span></span>
                                 </p>    
                                 <p class="TFSuploading2"> 已上传<i>0</i>%
                                 </p>                                
                            </div>
                            <div class="TFSuploaderror hidden">
                                <i class="J_re_upload loanInformation_upload_btn">重新上传</i>
                                <p>上传失败</p>
                            </div>
                            <div class="TFSimgOn hidden">
                                <i class="J_delete_upload loanInformation_upload_btn">删除</i>
                                <img src="../images/TFS/help_logo.jpg">
                            </div>
                        </div>
                    </li>
                    <li fileId="ensureOrgCodeCertificateUrl">
                        <h3 class="loanInformation_upload_list_title">组织机构代码证</h3>
                        <div class="loanInformation_upload_list_img">
                            <div class="TFSaddImg"></div>
                             <input type="file"  name="credit_file" id="ensureOrgCodeCertificateUrl"  errorMsg="请上传组织机构代码证"/>
                            <div class="TFSuploading TFSupload_JPG hidden">
                                 <p class="TFSuploading1">
                                     <span></span>
                                 </p>    
                                 <p class="TFSuploading2">   已上传<i>0</i>%
                                 </p>                                
                            </div>
                            <div class="TFSuploaderror hidden">
                                <i class="J_re_upload loanInformation_upload_btn">重新上传</i>
                                <p>上传失败</p>
                            </div>
                            <div class="TFSimgOn hidden">
                                <i class="J_delete_upload loanInformation_upload_btn">删除</i>
                                <img src="../images/TFS/help_logo.jpg">
                            </div>
                        </div>
                    </li>
                    <li fileId="ensureTaxRegisterCodeUrl">
                        <h3 class="loanInformation_upload_list_title">税务登记证</h3>
                        <div class="loanInformation_upload_list_img">
                            <div class="TFSaddImg"></div>
                              <input type="file"  name="credit_file" id="ensureTaxRegisterCodeUrl" errorMsg="请上传税务登记证"/>
                            <div class="TFSuploading TFSupload_JPG hidden">
                                 <p class="TFSuploading1">
                                     <span></span>
                                 </p>    
                                 <p class="TFSuploading2">     已上传<i>0</i>%
                                 </p>                                
                            </div>
                            <div class="TFSuploaderror hidden">
                                <i class="J_re_upload loanInformation_upload_btn">重新上传</i>
                                <p>上传失败</p>
                            </div>
                            <div class="TFSimgOn hidden">
                                <i class="J_delete_upload loanInformation_upload_btn">删除</i>
                                <img src="../images/TFS/help_logo.jpg">
                            </div>
                        </div>
                    </li>
                    <li fileId="ensureLicenseUrl">
                        <h3 class="loanInformation_upload_list_title">开户许可证</h3>
                        <div class="loanInformation_upload_list_img">
                            <div class="TFSaddImg"></div>
                               <input type="file"  name="credit_file" id="ensureLicenseUrl"  errorMsg="请上传开户许可证"/>
                            <div class="TFSuploading TFSupload_JPG hidden">
                                 <p class="TFSuploading1">
                                     <span></span>
                                 </p>    
                                 <p class="TFSuploading2">  已上传<i>0</i>%
                                 </p>                                
                            </div>
                            <div class="TFSuploaderror hidden">
                                <i class="J_re_upload loanInformation_upload_btn">重新上传</i>
                                <p>上传失败</p>
                            </div>
                            <div class="TFSimgOn hidden">
                                <i class="J_delete_upload loanInformation_upload_btn">删除</i>
                                <img src="../images/TFS/help_logo.jpg">
                            </div>
                        </div>
                    </li>
                    <li fileId="ensureLegalPersonUrl">
                        <h3 class="loanInformation_upload_list_title">法人身份证</h3>
                        <div class="loanInformation_upload_list_img">
                            <div class="TFSaddImg"></div>
                             <input type="file"  name="credit_file" id="ensureLegalPersonUrl" errorMsg="请上传法人身份证"/>
                            <div class="TFSuploading TFSupload_JPG hidden">
                                 <p class="TFSuploading1">
                                     <span></span>
                                 </p>    
                                 <p class="TFSuploading2">   已上传<i>0</i>%
                                 </p>                                
                            </div>
                            <div class="TFSuploaderror hidden">
                                <i class="J_re_upload loanInformation_upload_btn">重新上传</i>
                                <p>上传失败</p>
                            </div>
                            <div class="TFSimgOn hidden">
                                <i class="J_delete_upload loanInformation_upload_btn">删除</i>
                                <img src="../images/TFS/help_logo.jpg">
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
        
        <p class="loanInformation_upload_notice1">
            <label class="f_ui-checkbox-c3"><input id="protocolCheck" type="checkbox"><i></i> &nbsp;</label>我已阅读，<a href="javascript:void(0)" class="blue J_protocol">《用户须知》</a>并同意<a href="javascript:void(0)" class="blue J_protocol">《数据推送协议》</a>
        </p>

    </div>
    <div class="TFS_data_button">
        <a class=" btn_exit_long bnt_exit_padding12" href="javascript:void(0)" onclick="navObj.prev()">上一步</a><a class="btn btnNext" href="javascript:void(0)"  onclick="submitCrditApply();">保存并下一步</a>
    </div>
    <div style="height: 100px"></div>
<script type="text/javascript">
	var contextPath = "";
	var static_path="http://image.fangcang.com/upload/images/titanjr/credit_apply/${JR_USERID}/";
// 	var isCompanyEnsure = true;
// 	var isMarriedEnsure = false;
	
	function vlidateCompanyAccessory()
	{
		var result = true;
		var fileList= $('input[fileId]');
		var errorMsg = "";
		var index =0;
		fileList.each(function(){
  		    var fileId = $(this).attr('fileId');
  		    var fileObj = $('#' + fileId);
  		    if(!$('#' + fileId).parents().is(":hidden"))
  		    {
				if($(this).val() == null || $(this).val() == '')
				{
					index++;
					errorMsg+="("+index+") "+fileObj.attr("errorMsg")+"\n";
					result = false;
				}
	  		}
  	  	});
		
		if(index >0)
 		{
			window.top.createConfirm({
				title:'提示',
				content : '<div style="font-size:15px;line-height:30px;">'+errorMsg+'</div>',
				okValue:'确定',	
				skin : 'saas_confirm_singlebtn ',			
				ok : function(){
					
				},
				cancel : false  
			}); 

 		}
		else if(!$("#protocolCheck").is(':checked'))
		{
			
			window.top.createConfirm({
				title:'提示',
				content : '<div style="font-size:15px;line-height:30px;">请勾选并确认已经阅读用户须知并同意数据推送协议！</div>',
				okValue:'确定',	
				skin : 'saas_confirm_singlebtn ',			
				ok : function(){
					
				},
				cancel : false  
			}); 
			result = false;
		}
		
		return result;
	}
	
	function uploadError(ids)
	{
		$('#'+ids).prev('.TFSaddImg').addClass("hidden");
		$('#'+ids).parent().find(".TFSuploading").addClass("hidden");
    	$('#'+ids).parent().find(".TFSuploaderror").removeClass("hidden");
	}
	
	function uploadSucess(ids , fileName)
	{
		
		$('[fileId='+ids+']').val(fileName);
		
		var index1=fileName.lastIndexOf(".");
		var index2=fileName.length;
		var postf=fileName.substring(index1+1,index2);//后缀名
		var imgList ="/png/jpg/jpeg/";
		
		$('#'+ids).parent().find('.TFSimgOnBig').find('img').unbind("click");
		$('#'+ids).parent().find('.TFSimgOn').removeClass('TFSimgOnBig');
		
		//如果是图片则支持其预览
		if(imgList.indexOf(postf.toLowerCase()) !=-1)
		{
			$('#'+ids).parent().find('.TFSimgOn').addClass('TFSimgOnBig');
			$('#'+ids).parent().find('.TFSimgOn').find('img').attr("src",static_path+fileName +"?DateTime=" + new Date().getTime());
			bigImgShow();
		}else
		{
			$('#'+ids).parent().find('.TFSimgOn').find('img').attr("src",imageBasePath+'/images/TFS/help_logo.jpg');
		
			$('#'+ids).parent().find('.TFSimgOn').find("img").click(function(){
				 window.open(static_path+fileName);
			 });
		}
		
	}
	
	//这是一个附件上传之后的错误回调函数哦亲
	function handleError(s)
    {
		uploadError(s.fileElementId);
    }
      
    function bigImgShow(){              
        var _index=0; 
        var leftBtn,rightBtn,ulDiv;
        $(".TFSimgOnBig").find("img").unbind("click"); 
       $(".TFSimgOnBig").find("img").click(function(){
          var _div='<div tabindex="0" style=" position: fixed; left: 0px; top: 0px; width: 100%; height: 100%; overflow: hidden; -webkit-user-select: none; z-index: 1024; background-color: rgba(0, 0, 0,0.7);" class="bigImgShow_box_bg"><span class="left_btn"></span><span class="right_btn"></span><div class="bigImgShow_box"><span class="close_btn"></span><ul>';     
          _index=$(".TFSimgOnBig").find("img").index($(this));   
          for(var i=0;i< $(".TFSimgOnBig").find("img").length;i++){
              var img=$(".TFSimgOnBig").eq(i).find("img").attr("src");
               if(i==_index){
                 _div+="<li class='cur MissionRoom_annexList_li'><img src='"+img+"'></li>" 
              }else{
                _div+="<li class='MissionRoom_annexList_li'><img src='"+img+"'></li>"  
              }                              
          };
          _div+="</ul></div></div>";
          window.top.$("body").append(_div);
          leftBtn=window.parent.$(".left_btn");
          rightBtn=window.parent.$(".right_btn");
          var closeBtn=window.parent.$(".close_btn");
          var divId=window.parent.$(".bigImgShow_box_bg");
          ulDiv=window.parent.$(".bigImgShow_box").find("ul");
          if(_index==0){
              leftBtn.hide();
          };
          if(_index==$(".TFSimgOnBig").length-1){
              rightBtn.hide();
          };        
          leftBtn.on('click',function(){
             _index--;
             if(_index==0){
               leftBtn.hide();
             };
             rightBtn.show();
             showImg();
         }); 
         rightBtn.on('click',function(){                 
             _index++;
             if(_index==$(".TFSimgOnBig").length-1){
               rightBtn.hide();
             };
             leftBtn.show();
             showImg();
         });  
         closeBtn.live('click',function(){                 
            divId.remove();
         });          
      });
           
      function showImg(){
         ulDiv.find(".MissionRoom_annexList_li").hide().eq(_index).show(); 
        } 
      };   
      new bigImgShow();    
        //开始装B
       function loading(obj , callBack){
           var l1=obj.find("span");
           var l2=obj.find("i");
           l2.html(0);
           l1.css("width",0+"%");
           var i=0;
           var loadingJ=setInterval(function(){
                l1.css("width",i+"%");
                l2.html(i);
                if(i==90){
                    clearInterval(loadingJ);
                }
                i++;},50);
           return loadingJ;
       }
       //OK 装B完成
       function loadingOver(obj , callBack)
       {
    	   var l1=obj.find("span");
           var l2=obj.find("i");
           
           var i=parseInt(l2.text());
           
           var loadingN=setInterval(function(){
                 l1.css("width",i+"%");
                 l2.html(i);
                 if(i == 100){
                     clearInterval(loadingN);
                 }
                 i++;},20);
           
           setTimeout(function(){
        	  
             obj.addClass("hidden");  
             obj.parent().find(".TFSimgOn").removeClass("hidden").addClass("TFSimgOnBig");
             l2.html(0);
             
             callBack();
           },3000);
       }
       
    function companyAccessoryShow()
    {
    	$('#accessoryType').val($('input[name="assureType"]:checked').val());
    	
	      //个人担保
	      if($('input[name="assureType"]:checked').val() ==1)
	      {
	    	  $('input[ensureType="1"]').each(function(){
	    		  var fileId = $(this).attr('fileId');
	    		  $('li[fileId="'+fileId+'"]').show();
	    	  });
	    	  $('input[ensureType="2"]').each(function(){
	    		  
	    		  var fileId = $(this).attr('fileId');
	    		  $('li[fileId="'+fileId+'"]').hide();
	    	  });
	    	 //如果担保人未婚，则不需要传配偶附件信息
	    	  if($('select[field="marriageStatus"]').val() == 2)
	    	  {
	    		  $('li[fileId="spouseRegisteredUrl"]').hide();
	    		  $('li[fileId="spouseIdCardUrl"]').hide();
	    		  $('li[fileId="marriageUrl"]').hide();
	    	  }
	      }
	      else{
	    	  $('input[ensureType="2"]').each(function(){
	    		  var fileId = $(this).attr('fileId');
	    		  
	    		  $('li[fileId="'+fileId+'"]').show();
	    	  });
	    	  $('input[ensureType="1"]').each(function(){
	    		  var fileId = $(this).attr('fileId');
	    		  $('li[fileId="'+fileId+'"]').hide();
	    	  });
	      }      
    }
    
    
    function afterDoneAccessory()
    {
   	   $('#companyAccessory').find("[type='hidden']").each(function(){
         	 if($(this).attr('fileId') && $(this).val() != null &&  $(this).val() !='')
         	 {
 	        	 $('#'+$(this).attr('fileId')).prev('.TFSaddImg').addClass("hidden");
 	        	 $('#'+$(this).attr('fileId')).parent().find(".TFSuploaderror").addClass("hidden");
 	        	 $('#'+$(this).attr('fileId')).parent().find(".TFSuploading").addClass("hidden");
 	        	 $('#'+$(this).attr('fileId')).parent().find(".TFSimgOn").removeClass("hidden");
 	        	 $('#'+$(this).attr('fileId')).addClass("hidden");  
 	     		 uploadSucess( $(this).attr('fileId') ,  $(this).val());
         	 }
 	  	 });
           
    }
	

    function companyAccessoryRender(obj , path){
   
      contextPath = path;
   
   	  $(".J_protocol").live('click',function(){
             $.ajax({
                dataType : 'html',
                context: document.body,
                url : contextPath+'/loan/credit/creditProtocol.shtml',
                success : function(html){
                    var d =  window.top.dialog({
                        title: ' ',
                        padding: '0 0 20px 0',
                        content: html,
                        skin : 'saas_pop',                  
                        button : [ 
                            {
                                value: '确定',
                                skin : 'btn p_lr30',
                                callback: function () {      
                                    $("#protocolcheckbox").attr("checked",true)
                                },
                                autofocus: true
                            }
                        ],
                        close : function(){                        
                        }
                    }).showModal();
                }
            })
         });

    	
    	$(".TFSaddImg").on('click',function(){
             $(this).next("input").click();           
        });
    	
    	$(".TFSuploaderror").on('click',function(){
            $(this).parent().find('input').click();           
        });
        
    	  $(".J_delete_upload").live('click',function(){
    		  var obj =  $(this);
    		  var ids = $(this).parent().parent().find('input').attr("id");
    		  F.loading.show();
    		  $.ajax({
    				type:"post",
    				url: contextPath+'/loan/credit/delAccessory.shtml',	
    				data:{"typeId":$('[fileId='+ids+']').attr('typeId'),"fileName":$('[fileId='+ids+']').val()},
    				dataType:"json",
    				success:function(result){
    					if(result.code==1){
    						obj.parent().addClass("hidden").removeClass("TFSimgOnBig");
       						obj.parent().parent().find(".TFSaddImg").removeClass("hidden");
       						$('[fileId='+ids+']').val("");
       						
       						dataHandleObj.saveCurrZone();
       						
       						
    					}else{
    					}
    					
    				},
    				error:function(){
    				},
    				complete: function(){
    					 F.loading.hide();
    				}
    		 });
         });
         
    	
         $(".loanInformation_upload_list_img input").live("change",function(){
        	 
        	var ids =  $(this).attr('id');
        	//开始装B走进度条了哦 
	        var loadingJ =  loading($(this).parent().find(".TFSuploading"));
	        
	        $(this).prev('.TFSaddImg').addClass("hidden");
	        $(this).parent().find(".TFSuploaderror").addClass("hidden");
       	 	$(this).parent().find(".TFSuploading").removeClass("hidden");
	          
       	    $.ajaxFileUpload({
   	        	url: contextPath+'/loan/credit/upload.shtml',
   	            secureuri: false, 
   	            fileElementId: $(this).attr('id'), 
   	            dataType: 'json', 
   	            data:{"typeId":$('[fileId='+ids+']').attr('typeId')},
   	            success: function (result, status){
   	            	if(result.code==1)
   	            	{
	   	            	 if(loadingJ)
	   	            	 {
	   	            	  	clearInterval(loadingJ);
	   	            	 }
	   	            	 //假装让进度条走到100
	   	            	loadingOver($('#' + ids).parent().find(".TFSuploading") , function(){
	   	            				
	   	            		uploadSucess(ids , result.data);
	   	            		
	   	            		dataHandleObj.saveCurrZone();
	   	            	});
   					}
   	            	else
   	            	{
   						uploadError(ids);
   					}
   	            },
   	            error: function (data, status, e){
   	            	uploadError(ids);
   	            },
   	        });
          });
         
    }
</script>
