package org.example.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;

public class Vacancy {

    public static final Map<String, String> COLUMN_MAPPING = Map.of(
            "id", "id",
            "title", "title",
            "description", "description",
            "creation_date", "created",
            "visible", "visible",
            "city_id", "cityId",
            "file_id", "fileId"
    );
    private int id;
    private String title;
    private String description;
    private LocalDateTime created = LocalDateTime.now();
    private boolean visible;
    private int cityId;
    private int fileId;

    public Vacancy() {
    }

    public Vacancy(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Vacancy(int id, String title, String description, LocalDateTime created, boolean visible, int cityId, int fileId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.created = created;
        this.visible = visible;
        this.cityId = cityId;
        this.fileId = fileId;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getDateFormat() {
        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm");
        return created.format(FORMATTER);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacancy vacancy = (Vacancy) o;
        return id == vacancy.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
