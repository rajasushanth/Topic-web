package com.starkinc.wtopic.dto;

import java.util.List;

import com.starkinc.wtopic.entity.Topic;

public class TopicsDTO {
	
	private List<Topic> topics;
	private long total;
	private long page;
	private boolean isFirst;
	private boolean isLast;
	public List<Topic> getTopics() {
		return topics;
	}
	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public long getPage() {
		return page;
	}
	public void setPage(long page) {
		this.page = page;
	}
	public boolean isFirst() {
		return isFirst;
	}
	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}
	public boolean isLast() {
		return isLast;
	}
	public void setLast(boolean isLast) {
		this.isLast = isLast;
	}
	
	
	
	
}
