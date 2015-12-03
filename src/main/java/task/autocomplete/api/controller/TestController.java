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

@RestController
public class TestController {

	@Autowired
	private IAutoCompleteManager tagAutoCompleteManager;
	
	@RequestMapping(value = "/tag/{word}" , method = RequestMethod.GET)
	@ResponseBody
	public List<RedisVO> redis(@PathVariable("word") String word) {
        return tagAutoCompleteManager.complete(word);
    }
}
