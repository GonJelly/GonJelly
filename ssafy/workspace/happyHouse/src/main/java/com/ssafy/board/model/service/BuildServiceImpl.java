package com.ssafy.board.model.service;

import com.ssafy.board.model.BuildInfoDto;
import com.ssafy.board.model.mapper.BoardMapper;
import com.ssafy.board.model.mapper.BuildMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BuildServiceImpl implements BuildService {

    @Autowired
    private BuildMapper buildMapper;
    private static final int SEARCH_RANGE = 5;
    @Override
    public List<BuildInfoDto> searchApt(Map<String, Object> map) throws Exception {

        String key = map.get("key") == null ? "" : (String) map.get("key");
        if( key.equals("apartName") ) {
            map.put("key","h.apartmentName");
            return buildMapper.searchWord(map);
        } else {
            map.put("key","");
            return buildMapper.searchApt(map);
        }
    }

    @Override
    public int dealCount(int year) throws Exception {
        return buildMapper.dealCount(year);
    }

    @Override
    public BuildInfoDto dealDetail(String dealNo) throws Exception {
        return buildMapper.dealDetail(dealNo);
    }
}
