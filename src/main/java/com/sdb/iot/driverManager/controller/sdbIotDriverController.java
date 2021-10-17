package com.sdb.iot.driverManager.controller;

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
import com.sdb.iot.driverManager.domain.sdbIotDriver;
import com.sdb.iot.driverManager.service.IsdbIotDriverService;
import com.sdb.base.utils.poi.ExcelUtil;
import com.sdb.base.core.page.TableDataInfo;

/**
 * 驱动状态Controller
 * 
 * @author sdb
 * @date 2021-10-17
 */
@RestController
@RequestMapping("/driverManager/driver")
public class sdbIotDriverController extends BaseController
{
    @Autowired
    private IsdbIotDriverService sdbIotDriverService;

    /**
     * 查询驱动状态列表
     */
    @PreAuthorize("@ss.hasPermi('driverManager:driver:list')")
    @GetMapping("/list")
    public TableDataInfo list(sdbIotDriver sdbIotDriver)
    {
        startPage();
        List<sdbIotDriver> list = sdbIotDriverService.selectsdbIotDriverList(sdbIotDriver);
        return getDataTable(list);
    }

    /**
     * 导出驱动状态列表
     */
    @PreAuthorize("@ss.hasPermi('driverManager:driver:export')")
    @Log(title = "驱动状态", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(sdbIotDriver sdbIotDriver)
    {
        List<sdbIotDriver> list = sdbIotDriverService.selectsdbIotDriverList(sdbIotDriver);
        ExcelUtil<sdbIotDriver> util = new ExcelUtil<sdbIotDriver>(sdbIotDriver.class);
        return util.exportExcel(list, "驱动状态数据");
    }

    /**
     * 获取驱动状态详细信息
     */
    @PreAuthorize("@ss.hasPermi('driverManager:driver:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sdbIotDriverService.selectsdbIotDriverById(id));
    }

    /**
     * 新增驱动状态
     */
    @PreAuthorize("@ss.hasPermi('driverManager:driver:add')")
    @Log(title = "驱动状态", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody sdbIotDriver sdbIotDriver)
    {
        return toAjax(sdbIotDriverService.insertsdbIotDriver(sdbIotDriver));
    }

    /**
     * 修改驱动状态
     */
    @PreAuthorize("@ss.hasPermi('driverManager:driver:edit')")
    @Log(title = "驱动状态", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody sdbIotDriver sdbIotDriver)
    {
        return toAjax(sdbIotDriverService.updatesdbIotDriver(sdbIotDriver));
    }

    /**
     * 删除驱动状态
     */
    @PreAuthorize("@ss.hasPermi('driverManager:driver:remove')")
    @Log(title = "驱动状态", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sdbIotDriverService.deletesdbIotDriverByIds(ids));
    }
}
