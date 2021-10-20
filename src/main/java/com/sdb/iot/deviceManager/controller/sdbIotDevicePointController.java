package com.sdb.iot.deviceManager.controller;

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
import com.sdb.iot.deviceManager.domain.sdbIotDevicePoint;
import com.sdb.iot.deviceManager.service.IsdbIotDevicePointService;
import com.sdb.base.utils.poi.ExcelUtil;
import com.sdb.base.core.page.TableDataInfo;

/**
 * 位号配置Controller
 * 
 * @author sdb
 * @date 2021-10-20
 */
@RestController
@RequestMapping("/deviceManager/devicePoint")
public class sdbIotDevicePointController extends BaseController
{
    @Autowired
    private IsdbIotDevicePointService sdbIotDevicePointService;

    /**
     * 查询位号配置列表
     */
    @PreAuthorize("@ss.hasPermi('deviceManager:devicePoint:list')")
    @GetMapping("/list")
    public TableDataInfo list(sdbIotDevicePoint sdbIotDevicePoint)
    {
        startPage();
        List<sdbIotDevicePoint> list = sdbIotDevicePointService.selectsdbIotDevicePointList(sdbIotDevicePoint);
        return getDataTable(list);
    }

    /**
     * 导出位号配置列表
     */
    @PreAuthorize("@ss.hasPermi('deviceManager:devicePoint:export')")
    @Log(title = "位号配置", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(sdbIotDevicePoint sdbIotDevicePoint)
    {
        List<sdbIotDevicePoint> list = sdbIotDevicePointService.selectsdbIotDevicePointList(sdbIotDevicePoint);
        ExcelUtil<sdbIotDevicePoint> util = new ExcelUtil<sdbIotDevicePoint>(sdbIotDevicePoint.class);
        return util.exportExcel(list, "位号配置数据");
    }

    /**
     * 获取位号配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('deviceManager:devicePoint:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sdbIotDevicePointService.selectsdbIotDevicePointById(id));
    }

    /**
     * 新增位号配置
     */
    @PreAuthorize("@ss.hasPermi('deviceManager:devicePoint:add')")
    @Log(title = "位号配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody sdbIotDevicePoint sdbIotDevicePoint)
    {
        return toAjax(sdbIotDevicePointService.insertsdbIotDevicePoint(sdbIotDevicePoint));
    }

    /**
     * 修改位号配置
     */
    @PreAuthorize("@ss.hasPermi('deviceManager:devicePoint:edit')")
    @Log(title = "位号配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody sdbIotDevicePoint sdbIotDevicePoint)
    {
        return toAjax(sdbIotDevicePointService.updatesdbIotDevicePoint(sdbIotDevicePoint));
    }

    /**
     * 删除位号配置
     */
    @PreAuthorize("@ss.hasPermi('deviceManager:devicePoint:remove')")
    @Log(title = "位号配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sdbIotDevicePointService.deletesdbIotDevicePointByIds(ids));
    }
}
