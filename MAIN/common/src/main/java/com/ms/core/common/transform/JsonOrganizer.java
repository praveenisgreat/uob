package com.ms.core.common.transform;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.Predicate;

import org.springframework.security.core.Authentication;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonOrganizer {
	
	private JsonNode root;
    private Predicate<JsonNode> toRemoveCondition;
    
    private Authentication principals;
    private String targetRoot;
    private String keyToRemove;
	
    public JsonOrganizer() {
    	
    }
    
	public JsonOrganizer(JsonNode node, Predicate<JsonNode> toRemoveCondition) {
        this.root = Objects.requireNonNull(node);
        this.toRemoveCondition = Objects.requireNonNull(toRemoveCondition);
    }
	
	public JsonOrganizer(Authentication principals, String targetRoot, String keyToRemove) {
        this.principals = principals;
        this.targetRoot = targetRoot;
        this.keyToRemove = keyToRemove;
    }

	public JsonNode removeByField() {
		ObjectMapper mapper = new ObjectMapper();
    	mapper.enable(SerializationFeature.INDENT_OUTPUT);
    	root = mapper.valueToTree(principals.getPrincipal());
    	
        JsonNode ability = root.path(targetRoot).get(0);
        System.out.println("-->"+root.path(targetRoot).get(0).get("action").toString().toLowerCase());
        ObjectNode object = (ObjectNode) ability;
        Iterator<Map.Entry<String, JsonNode>> fields = object.fields();
        // remove id field
        while(fields.hasNext()) {
        	Map.Entry<String, JsonNode> field = fields.next();
        	JsonNode value = field.getValue();
        	String key = field.getKey();
        	if(key==keyToRemove) {
        		fields.remove();
        	}
        }
        
        //lower case
        ObjectNode nodeObj = (ObjectNode) object;
        //object.get("subject").toString().toLowerCase()
        nodeObj.put("action", "manage");
        nodeObj.put("subject", "all");
        nodeObj.textValue();
        return root;
    }
   
	private void processRemoveByFiled(JsonNode root) {
        if (root.isObject()) {
        
        ObjectNode object = (ObjectNode) root;
        Iterator<Map.Entry<String, JsonNode>> fields = object.fields();
   
        Iterator<String> names = root.findParent("id").fieldNames();
        while(names.hasNext()) {
        	String name = names.next();
        	
        }
        
        while (fields.hasNext()) {
        	Map.Entry<String, JsonNode> field = fields.next();
        	JsonNode valueToCheck = field.getValue();
        	String key = field.getKey();
        	
            if (valueToCheck.isContainerNode()) {
            	processRemoveByFiled(valueToCheck);
            } else {
            	if(key == keyToRemove) {
            		fields.remove();
            	}
            } 
        }
        } else if (root.isArray()) {
            ArrayNode array = (ArrayNode) root;
            array.elements().forEachRemaining(this::processRemoveByFiled);
        }
    }
	
    private void processRemoveByValue(JsonNode node) {
        if (node.isObject()) {
            ObjectNode object = (ObjectNode) node;
            Iterator<Map.Entry<String, JsonNode>> fields = object.fields();
            while (fields.hasNext()) {
            	
                Map.Entry<String, JsonNode> field = fields.next();
                System.out.println("Process="+field);
                JsonNode valueToCheck = field.getValue();
                if (valueToCheck.isContainerNode()) {
                	processRemoveByValue(valueToCheck);
                } else if (toRemoveCondition.test(valueToCheck)) {
                    fields.remove();
                }
            }
        } else if (node.isArray()) {
            ArrayNode array = (ArrayNode) node;
            array.elements().forEachRemaining(this::processRemoveByValue);
        }
    }
}
