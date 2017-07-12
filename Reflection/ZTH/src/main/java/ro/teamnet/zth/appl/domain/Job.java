package ro.teamnet.zth.appl.domain;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

/**
 * Created by Gianina.Carp on 7/12/2017.
 */
@Table(name = "jobs")
public class Job {
    @Id(name = "job_id")
    private String id;

    @Column(name = "job_title")
    private String job_title;

    @Column(name = "min_salary")
    private Long min_salary;

    @Column(name = "max_salary")
    private Long max_salary;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Job)) return false;

        Job job = (Job) o;

        if (!getId().equals(job.getId())) return false;
        if (!getJob_title().equals(job.getJob_title())) return false;
        if (!getMin_salary().equals(job.getMin_salary())) return false;
        return getMax_salary().equals(job.getMax_salary());
    }

    @Override
    public String toString() {
        return "Job{" +
                "id='" + id + '\'' +
                ", job_title='" + job_title + '\'' +
                ", min_salary=" + min_salary +
                ", max_salary=" + max_salary +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public Long getMin_salary() {
        return min_salary;
    }

    public void setMin_salary(Long min_salary) {
        this.min_salary = min_salary;
    }

    public Long getMax_salary() {
        return max_salary;
    }

    public void setMax_salary(Long max_salary) {
        this.max_salary = max_salary;
    }
}
