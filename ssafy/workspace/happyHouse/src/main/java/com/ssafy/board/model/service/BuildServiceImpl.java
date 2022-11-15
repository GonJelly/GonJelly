package com.ssafy.board.model.service;

import com.ssafy.Util.PageNavigation;
import com.ssafy.Util.SizeConstant;
import com.ssafy.board.model.BuildInfoDto;
import com.ssafy.board.model.mapper.BoardMapper;
import com.ssafy.board.model.mapper.BuildMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BuildServiceImpl implements BuildService {

    @Autowired
    private BuildMapper buildMapper;
    @Override
    public List<BuildInfoDto> searchApt(Map<String, Object> map) throws Exception {
        String pgno = (String) map.get("pgno");
        Map<String,Object> param = new HashMap<>();
        param.put("start", SizeConstant.LIST_SIZE * (Integer.parseInt(pgno) - 1) );
        param.put("limitSize",SizeConstant.LIST_SIZE);
        String key = (String) map.get("key");
        if ("apartName".equals(key))
            key = "h.apartmentName";
        param.put("key", key == null ? "" : key);
        param.put("word", map.get("word") == null ? "" : map.get("word"));
        param.put("regCode", map.get("regCode") == null ? "" : map.get("regCode"));
        param.put("year", map.get("year") == null ? "" : map.get("year"));
        param.put("month", map.get("month") == null ? "" : map.get("month"));
        return buildMapper.searchApt(param);
    }

    @Override
    public BuildInfoDto dealDetail(String dealNo) throws Exception {
        return buildMapper.dealDetail(dealNo);
    }

    public PageNavigation makePageNavigation(Map<String, Object> map) throws Exception {
        PageNavigation pageNavigation = new PageNavigation();

        int naviSize = SizeConstant.NAVIGATION_SIZE;
        int sizePerPage = SizeConstant.LIST_SIZE;
        int currentPage = Integer.parseInt((String) map.get("pgno"));

        pageNavigation.setCurrentPage(currentPage);
        pageNavigation.setNaviSize(naviSize);
        Map<String,Object> param = new HashMap<>();
        String key = (String) map.get("key");
        if ("apartName".equals(key))
            key = "h.apartmentName";
        param.put("key", key == null ? "" : key);
        param.put("word", map.get("word") == null ? "" : map.get("word"));
        param.put("regCode", map.get("regCode") == null ? "" : map.get("regCode"));
        param.put("year", map.get("year") == null ? "" : map.get("year"));
        param.put("month", map.get("month") == null ? "" : map.get("month"));
        int totalCount = buildMapper.getTotalBuildDeal(param);
        pageNavigation.setTotalCount(totalCount);
        int totalPageCount = (totalCount - 1) / sizePerPage + 1;
        pageNavigation.setTotalPageCount(totalPageCount);
        boolean startRange = currentPage <= naviSize;
        pageNavigation.setStartRange(startRange);
        boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
        pageNavigation.setEndRange(endRange);
        int startPage = (currentPage - 1) / naviSize * naviSize + 1;
        pageNavigation.setStartPage(startPage);
        int endPage = startPage + naviSize - 1;
        pageNavigation.setEndPage(endPage);
        if( totalPageCount < endPage ) {
            pageNavigation.setEndPage(totalPageCount);
        }

        return pageNavigation;
    }
}
