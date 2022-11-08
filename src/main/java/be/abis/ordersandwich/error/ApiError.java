package be.abis.ordersandwich.error;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ApiError {
    private String title;
    private int status;
    private String description;
    private String type = "about:blank";
    private String instance = "";
    @JsonProperty("invalid-params")
    private List<ValidationError> invalidParams = new ArrayList<ValidationError>();
    public ApiError(){}
    public ApiError(String title, int status, String description) {
        this.title = title;
        this.status = status;
        this.description = description;
    }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getInstance() { return instance; }
    public void setInstance(String instance) { this.instance = instance; }

    public List<ValidationError> getInvalidParams() {
        return invalidParams;
    }

    public void setInvalidParams(List<ValidationError> invalidParams) {
        this.invalidParams = invalidParams;
    }
}
