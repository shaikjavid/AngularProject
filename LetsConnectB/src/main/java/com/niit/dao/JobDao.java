package com.niit.dao;

import java.util.List;

import com.niit.model.MyJob;

public interface JobDao {
public List<MyJob> list(Integer userId);
public List<MyJob> appliedJobs(Integer userId);
	
	public MyJob get(int id);

	public void add(MyJob job);

	public void delete(int id);
	
	
}
