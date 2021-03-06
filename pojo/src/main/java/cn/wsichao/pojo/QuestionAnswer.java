package cn.wsichao.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class QuestionAnswer implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long questionId;// 此答案对应的问题
    private Long userId;// 回答者
    private String username;// 回答者姓名
    private Long parentId;// 如果此答案是某个其他答案的补充回答，使用此字段标识被补充回答的答案id
    private Date createTime;// 回答时间
    private String content;// 回答的内容
    private Boolean isAdopted;// 是否被采纳为标准答案

    List<QuestionAnswer> childQuestionAnswerList;//子回答（辅助属性）

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        QuestionAnswer other = (QuestionAnswer) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getIsAdopted() {
        return isAdopted;
    }

    public void setIsAdopted(Boolean isAdopted) {
        this.isAdopted = isAdopted;
    }

    public List<QuestionAnswer> getChildQuestionAnswerList() {
        return childQuestionAnswerList;
    }

    public void setChildQuestionAnswerList(List<QuestionAnswer> childQuestionAnswerList) {
        this.childQuestionAnswerList = childQuestionAnswerList;
    }

}
