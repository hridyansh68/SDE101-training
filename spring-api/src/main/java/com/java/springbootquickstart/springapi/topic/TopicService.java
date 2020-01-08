package com.java.springbootquickstart.springapi.topic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
	
	    @Autowired
	    private TopicRepository topicRepository;
	
        public List<Topic> topics = new ArrayList<>(Arrays.asList(
				new Topic("spring","springFramework","SpringframeworkDescription"),
				new Topic("java","Core Java","Core Java Description"),
				new Topic("javascript","Javascript", "Javascript Description")
				));
        
        public List<Topic> getAllTopics(){
        	List<Topic> topics = new ArrayList<>();
        	topicRepository.findAll().forEach(topics::add);
        	return topics;
        }
        
        public Optional<Topic> getTopic(String id) {
        	return topicRepository.findById(id);
        }

		public void addTopic(Topic topic) {
			topicRepository.save(topic);
			
		}

		public void updateTopic(Topic topic, String id) {
			// TODO Auto-generated method stub
			
			topicRepository.save(topic);
			
		}


		public void deleteTopic(String id) {
			// TODO Auto-generated method stub
			topicRepository.deleteById(id);
			
		}
}
