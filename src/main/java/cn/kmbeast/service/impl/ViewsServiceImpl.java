package cn.kmbeast.service.impl;

import cn.kmbeast.mapper.ContentNetMapper;
import cn.kmbeast.mapper.GourmetMapper;
import cn.kmbeast.mapper.InteractionMapper;
import cn.kmbeast.mapper.UserMapper;
import cn.kmbeast.pojo.api.ApiResult;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.ContentNetQueryDto;
import cn.kmbeast.pojo.dto.query.extend.GourmetQueryDto;
import cn.kmbeast.pojo.dto.query.extend.InteractionQueryDto;
import cn.kmbeast.pojo.dto.query.extend.UserQueryDto;
import cn.kmbeast.pojo.vo.ChartVO;
import cn.kmbeast.service.ViewsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 首页可视化
 */
@Service
public class ViewsServiceImpl implements ViewsService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private GourmetMapper gourmetMapper;
    @Resource
    private ContentNetMapper contentNetMapper;
    @Resource
    private InteractionMapper interactionMapper;

    /**
     * 统计一些系统的基础数据
     *
     * @return Result<List < ChartVO>>
     */
    @Override
    public Result<List<ChartVO>> staticControls() {
        List<ChartVO> chartVOS = new ArrayList<>();
        // 1. 用户数
        int userCount = userMapper.queryCount(new UserQueryDto());
        change(userCount, "系统用户", chartVOS);
        // 2. 美食做法数
        int gourmetCount = gourmetMapper.queryCount(new GourmetQueryDto());
        change(gourmetCount, "美食做法篇章", chartVOS);
        // 3. 内容分享数
        int contentNetCount = contentNetMapper.queryCount(new ContentNetQueryDto());
        change(contentNetCount, "内容分享数", chartVOS);
        // 4. 美食做法
        Integer interactionCount = interactionMapper.queryCount(new InteractionQueryDto());
        change(interactionCount, "互动量", chartVOS);
        return ApiResult.success(chartVOS);
    }

    /**
     * 参数处理
     *
     * @param count    总数目
     * @param name     统计项
     * @param chartVOS 装它们的集合
     */
    private void change(Integer count, String name, List<ChartVO> chartVOS) {
        ChartVO chartVO = new ChartVO(name, count);
        chartVOS.add(chartVO);
    }


}
