package com.redrabbit.cloud.core.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author redrabbit
 * @since 2020-12-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="UserInfo对象", description="用户表")
public class UserInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户uid")
    private String uid;

    @ApiModelProperty(value = "登录账号")
    private String username;

    @ApiModelProperty(value = "登录密码")
    private String password;

    @ApiModelProperty(value = "用户真实姓名")
    private String name;

    @ApiModelProperty(value = "用户身份证号")
    private String cardId;

    @ApiModelProperty(value = "用户级别")
    private String level;

    @ApiModelProperty(value = "用户状态：0:正常状态,1：用户被锁定")
    private String state;


}
