package com.xiexin.bean;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * userinfo
 * @author 
 */
@ToString// lombok的插件的注解，不用写tostring
@Data  //不用写 get set ... @Getter l
public class Userinfo implements Serializable {
    private Integer id;

    private String username;

    private String password;

    private static final long serialVersionUID = 1L;

}