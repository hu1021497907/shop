package com.axh.form;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class UserForm {
//    @NotNull(message = "id不能为空")
    private Integer id;
    @NotNull(message = "username不能为空")
    private String username;
    @NotNull(message = "密码不能为空")
    private String password;
    @Email(message = "邮箱格式不正确")
    private String email;
    @Pattern(regexp = "^1(3|4|5|6|7|8|9)\\d{9}$",message = "手机号有误")
    private String phone;
    @NotNull(message = "问题不能为空")
    private String question;
    @NotNull(message = "答案不能为空")
    private String answer;

}
