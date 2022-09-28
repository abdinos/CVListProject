package myboot.app1.service;

import myboot.app1.dao.CurriculumVitaeRepository;
import myboot.app1.model.Activity;
import myboot.app1.model.CurriculumVitae;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurriculumVitaeService {
    @Autowired
    CurriculumVitaeRepository curriculumVitaeRepository;

    public CurriculumVitae saveCV(CurriculumVitae cv) {
        return curriculumVitaeRepository.save(cv);
    }

    public CurriculumVitae getCV(int id) {
        return curriculumVitaeRepository.findById(id).get();
    }

    public String deleteCV(int id) {
        curriculumVitaeRepository.deleteById(id);
        return "CV deleted " + id;
    }
    public CurriculumVitae updateCV(CurriculumVitae cv){
        if (curriculumVitaeRepository.findById(cv.getId()).isEmpty()){
            curriculumVitaeRepository.save(cv); }
        CurriculumVitae cv2 = curriculumVitaeRepository.findById(cv.getId()).get();
        cv2.setActivities(cv.getActivities());
        curriculumVitaeRepository.save(cv2);
        return cv;

    }
}
