package task.autocomplete.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import task.autocomplete.api.redis.manager.IAutoCompleteManager;
import task.autocomplete.api.redis.vo.RedisVO;
import task.autocomplete.api.vo.ResponseVO;

@RestController
public class TestController {

	@Autowired
	private IAutoCompleteManager tagAutoCompleteManager;
	
	@RequestMapping(value = "/api/{word}" , method = RequestMethod.GET)
	@ResponseBody
	public List<RedisVO> complete(@PathVariable("word") String word) {
        return tagAutoCompleteManager.complete(word);
    }
	
	@RequestMapping(value = "/api/add/{word}" , method = RequestMethod.PUT)
	@ResponseBody
	public ResponseVO add(@PathVariable("word") String word) {
        tagAutoCompleteManager.addWord(word);
        return new ResponseVO();
    }
	
	@RequestMapping(value = "/api/{word}" , method = RequestMethod.POST)
	@ResponseBody
	public ResponseVO incr(@PathVariable("word") String word) {
		tagAutoCompleteManager.incr(word);
		return new ResponseVO();
    }
}
