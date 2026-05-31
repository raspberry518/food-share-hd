package cn.kmbeast.service;

import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.vo.GourmetListVO;
import cn.kmbeast.pojo.vo.GourmetVO;

import java.util.List;

public interface RecommendService {

    Result<List<GourmetListVO>> recommendGourmet(Integer item);
}