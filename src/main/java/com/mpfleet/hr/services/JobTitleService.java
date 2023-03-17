package com.mpfleet.hr.services;

import com.mpfleet.hr.models.JobTitle;
import com.mpfleet.hr.repositories.JobTitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobTitleService {

	private final JobTitleRepository jobTitleRepository;

	@Autowired
	public JobTitleService(JobTitleRepository jobTitleRepository) {
		this.jobTitleRepository = jobTitleRepository;
	}

	public List<JobTitle> findAll(){
		return jobTitleRepository.findAll();
	}	

	public Optional<JobTitle> findById(Long id) {
		return jobTitleRepository.findById(id);
	}	

	public void delete(Long id) {
		jobTitleRepository.deleteById(id);
	}

	public void save(JobTitle jobTitle) {
		jobTitleRepository.save(jobTitle);
	}
}
