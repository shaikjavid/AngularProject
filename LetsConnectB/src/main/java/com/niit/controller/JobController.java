package com.niit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.JobApplicantDao;
import com.niit.dao.JobDao;
import com.niit.model.JobApplicants;
import com.niit.model.MyJob;

@RestController
public class JobController {
	
	@Autowired
	JobDao jobDao;
	
	@Autowired
	JobApplicantDao jobApplicantDao;
	@PostMapping("/jobposting")
	public ResponseEntity<MyJob> jobPosting(@RequestBody MyJob job)
	{
		jobDao.add(job);
		return new ResponseEntity<MyJob>(job, HttpStatus.OK);
	}

	
	@GetMapping("/alljobs/{id}")
	public ResponseEntity<List<MyJob>> allJobs(@PathVariable("id") Integer userId)
	{
 		List<MyJob> list=jobDao.list(userId);
 		for(MyJob j: list)
 		{
 			System.out.println(j.getDated());
 		}
		return new ResponseEntity<List<MyJob>>(list,HttpStatus.OK);
	}
	
	@PostMapping("/applyJob")
	public ResponseEntity<List<MyJob>> applyJob(@RequestBody JobApplicants jobApplicants)
	{
		jobApplicantDao.addJob(jobApplicants);
		
	return new ResponseEntity<>(jobDao.list(jobApplicants.getUserId()),HttpStatus.OK)	;
		
	}
	
	@GetMapping("/appliedJobs/{id}")
	public ResponseEntity<List<MyJob>> applyJob(@PathVariable("id") Integer userId)
	{
		
		
	return new ResponseEntity<List<MyJob>>(jobDao.appliedJobs(userId),HttpStatus.OK)	;
		
	}
}