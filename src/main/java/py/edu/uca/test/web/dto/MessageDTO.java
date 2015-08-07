package py.edu.uca.test.web.dto;

public class MessageDTO {
    private String level;
    private String key;
    private String description;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "MsgDto [level=" + level + ", key=" + key + ", description="
                + description + "]";
    }
}
