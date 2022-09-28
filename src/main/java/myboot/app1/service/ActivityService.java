package myboot.app1.service;

import myboot.app1.dao.ActivityRepository;
import myboot.app1.model.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {
    @Autowired
    ActivityRepository activityRepository;

    public Activity saveActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    public Activity getActivity(int id) {
        return activityRepository.findById(id).get();
    }

    public String deleteActivity(int id) {
        activityRepository.deleteById(id);
        return "activity deleted " + id;
    }
    public Activity updateActivity(Activity activity){
        if (activityRepository.findById(activity.getId()).isEmpty()){
           activityRepository.save(activity); }
        Activity ac = activityRepository.findById(activity.getId()).get();
        ac.setDescription(activity.getDescription());
        ac.setNature(activity.getNature());
        ac.setTitle(activity.getTitle());
        ac.setYear(activity.getYear());
        ac.setWebsite(activity.getWebsite());
        activityRepository.save(ac);
        return ac;
    }

}
