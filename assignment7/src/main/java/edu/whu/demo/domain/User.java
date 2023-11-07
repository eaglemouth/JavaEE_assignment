package edu.whu.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName("user")
public class User {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    String id;

    private String password; //密码在数据库中需要加密保存

}
