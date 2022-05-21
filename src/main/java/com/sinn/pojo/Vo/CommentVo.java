package com.sinn.pojo.Vo;

import com.sinn.pojo.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/5/16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentVo extends Comment {
    //回复评论
    private List<CommentVo> replyComments = new LinkedList<>();

    //父节点名字
    private String parentCommentName;
}
