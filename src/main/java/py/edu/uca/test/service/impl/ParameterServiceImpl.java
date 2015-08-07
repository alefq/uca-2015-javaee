package py.edu.uca.test.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.edu.uca.test.domain.ParameterEntity;
import py.edu.uca.test.service.ParameterService;
import py.edu.uca.test.web.dto.ParameterDTO;
import py.edu.uca.test.web.repository.ParameterRepository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class ParameterServiceImpl implements ParameterService {

	private static final Logger logger = LoggerFactory.getLogger(ParameterServiceImpl.class);
	
	 private Date today = new Date();
	
	@Resource
    private
    ParameterRepository parameterRepository;
	
	@Override
	public List<ParameterDTO> findAll() {
		List<ParameterEntity> frombd = getParameterRepository().findAll();
		List<ParameterDTO> ret = new ArrayList<ParameterDTO>();
		for(ParameterEntity content: frombd){
			ParameterDTO dto = new ParameterDTO();
			BeanUtils.copyProperties(content, dto);
            ret.add(dto);
		}
		return ret;
	}


    @Override
    public ParameterDTO findByLabel(String p_goal) {
        ParameterDTO dto = new ParameterDTO();
        ParameterEntity entity = getParameterRepository().findByLabel(p_goal);
        if(entity != null) {
            BeanUtils.copyProperties(entity, dto);
        } else {
            logger.error("No se encuentra el parametro con label: " + p_goal);
        }
        return dto;
    }

    @Override
    @Transactional
    public ParameterDTO updateValueByLabel(String label, String value) {
        ParameterEntity entity = getParameterRepository().findByLabel(label);
        entity.setValue(value);
        ParameterDTO dto = new ParameterDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    @Override
    public String getStringByLabel(String label) {
        String value = null;
        ParameterEntity parameter = getParameterRepository().findByLabel(label);
        if(parameter != null) {
            value = parameter.getValue();
        }
        return value;
    }

    public Date getToday() {
        return today;
    }

    public void setToday(Date p_today) {
        today = p_today;
    }

    public ParameterRepository getParameterRepository() {
        return parameterRepository;
    }

    public void setParameterRepository(ParameterRepository p_parameterRepository) {
        parameterRepository = p_parameterRepository;
    }
}
