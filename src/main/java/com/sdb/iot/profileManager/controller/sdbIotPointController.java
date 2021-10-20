package com.sdb.iot.profileManager.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sdb.base.annotation.Log;
import com.sdb.base.core.controller.BaseController;
import com.sdb.base.core.domain.AjaxResult;
import com.sdb.base.enums.BusinessType;
import com.sdb.iot.profileManager.domain.sdbIotPoint;
import com.sdb.iot.profileManager.service.IsdbIotPointService;
import com.sdb.base.utils.poi.ExcelUtil;
import com.sdb.base.core.page.TableDataInfo;

/**
 * 位号Controller
 * 
 * @author sdb
 * @date 2021-10-20
 */
@RestController
@RequestMapping("/profileManager/point")
public class sdbIotPointController extends BaseController
{
    @Autowired
    private IsdbIotPointService sdbIotPointService;

    /**
     * 查询位号列表
     */
    @PreAuthorize("@ss.hasPermi('profileManager:point:list')")
    @GetMapping("/list")
    public TableDataInfo list(sdbIotPoint sdbIotPoint)
    {
        startPage();
        List<sdbIotPoint> list = sdbIotPointService.selectsdbIotPointList(sdbIotPoint);
        return getDataTable(list);
    }

    /**
     * 导出位号列表
     */
    @PreAuthorize("@ss.hasPermi('profileManager:point:export')")
    @Log(title = "位号", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(sdbIotPoint sdbIotPoint)
    {
        List<sdbIotPoint> list = sdbIotPointService.selectsdbIotPointList(sdbIotPoint);
        ExcelUtil<sdbIotPoint> util = new ExcelUtil<sdbIotPoint>(sdbIotPoint.class);
        return util.exportExcel(list, "位号数据");
    }

    /**
     * 获取位号详细信息
     */
    @PreAuthorize("@ss.hasPermi('profileManager:point:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sdbIotPointService.selectsdbIotPointById(id));
    }

    /**
     * 新增位号
     */
    @PreAuthorize("@ss.hasPermi('profileManager:point:add')")
    @Log(title = "位号", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody sdbIotPoint sdbIotPoint)
    {
        return toAjax(sdbIotPointService.insertsdbIotPoint(sdbIotPoint));
    }

    /**
     * 修改位号
     */
    @PreAuthorize("@ss.hasPermi('profileManager:point:edit')")
    @Log(title = "位号", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody sdbIotPoint sdbIotPoint)
    {
        return toAjax(sdbIotPointService.updatesdbIotPoint(sdbIotPoint));
    }

    /**
     * 删除位号
     */
    @PreAuthorize("@ss.hasPermi('profileManager:point:remove')")
    @Log(title = "位号", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sdbIotPointService.deletesdbIotPointByIds(ids));
    }
}
