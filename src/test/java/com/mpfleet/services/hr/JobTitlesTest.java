package com.mpfleet.services.hr;

import com.mpfleet.hr.models.JobTitle;
import com.mpfleet.hr.repositories.JobTitleRepository;
import com.mpfleet.hr.services.JobTitleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JobTitlesTest {

    @Mock
    private JobTitleRepository jobTitleRepository;

    @InjectMocks
    private JobTitleService jobTitleService;

    @Test
    public void testFindAll() {
        List<JobTitle> expectedJobTitles = Arrays.asList(
                new JobTitle(),
                new JobTitle()
        );

        when(jobTitleRepository.findAll()).thenReturn(expectedJobTitles);

        List<JobTitle> actualJobTitles = jobTitleService.findAll();

        assertEquals(expectedJobTitles, actualJobTitles);
    }

    @Test
    public void testFindById() {
        JobTitle expectedJobTitle = new JobTitle();
        when(jobTitleRepository.findById(expectedJobTitle.getId())).thenReturn(Optional.of(expectedJobTitle));

        Optional<JobTitle> actualJobTitle = jobTitleService.findById(expectedJobTitle.getId());

        assertTrue(actualJobTitle.isPresent());
        assertEquals(expectedJobTitle, actualJobTitle.get());
    }

    @Test
    public void testDelete() {
        jobTitleService.delete(1L);
        Mockito.verify(jobTitleRepository).deleteById(1L);
    }

    @Test
    public void testSave() {
        JobTitle jobTitleToSave = new JobTitle();
        jobTitleToSave.setId(null); jobTitleToSave.setDescription("Manager");
        JobTitle expectedSavedJobTitle = new JobTitle();
        expectedSavedJobTitle.setId(1L); jobTitleToSave.setDescription("Manager");

        when(jobTitleRepository.save(jobTitleToSave)).thenReturn(expectedSavedJobTitle);

        jobTitleService.save(jobTitleToSave);
        Mockito.verify(jobTitleRepository).save(jobTitleToSave);
    }
}
