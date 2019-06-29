package cn.wannengde.community.dto;

/*
@author kbq
@date  2019/6/29 - 18:43
*/
public class GitHubUser {
    private String name;
    private Long id;
    private String bio;

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public String getBio() {
        return bio;
    }
}
