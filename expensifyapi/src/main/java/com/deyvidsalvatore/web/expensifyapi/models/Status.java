package com.deyvidsalvatore.web.expensifyapi.models;

import java.io.Serial;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "statuses")
public class Status {
	
	@Serial
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Enumerated(value = EnumType.STRING)
	private State state;
	private String reviewedBy;
	private LocalDate reviewDate;
	private String comment;
	
	public enum State {
		IN_REVIEW,
		APPROVED,
		REJECTED
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getReviewedBy() {
		return reviewedBy;
	}

	public void setReviewedBy(String reviewedBy) {
		this.reviewedBy = reviewedBy;
	}

	public LocalDate getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(LocalDate reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public int hashCode() {
		return Objects.hash(comment, id, reviewDate, reviewedBy, state);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Status other = (Status) obj;
		return Objects.equals(comment, other.comment) && Objects.equals(id, other.id)
				&& Objects.equals(reviewDate, other.reviewDate) && Objects.equals(reviewedBy, other.reviewedBy)
				&& state == other.state;
	}
	
	/* Builder */
    private Status() {}
    
    private Status(StatusBuilder builder) {
		this.state = builder.state;
		this.reviewedBy = builder.reviewedBy;
		this.reviewDate = builder.reviewDate;
		this.comment = builder.comment;
	}



	public static class StatusBuilder {
        private State state;
        private String reviewedBy;
        private LocalDate reviewDate;
        private String comment;

        public StatusBuilder() {
        }

        public StatusBuilder state(State state) {
            this.state = state;
            return this;
        }

        public StatusBuilder reviewedBy(String reviewedBy) {
            this.reviewedBy = reviewedBy;
            return this;
        }

        public StatusBuilder reviewDate(LocalDate reviewDate) {
            this.reviewDate = reviewDate;
            return this;
        }

        public StatusBuilder comment(String comment) {
            this.comment = comment;
            return this;
        }

        public Status build() {
            return new Status(this);
        }
    }
	
	public static StatusBuilder builder() {
		return new StatusBuilder();
	}
}
