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
import com.sdb.iot.deviceManager.domain.sdbIotDevice;
import com.sdb.iot.deviceManager.service.IsdbIotDeviceService;
import com.sdb.base.utils.poi.ExcelUtil;
import com.sdb.base.core.page.TableDataInfo;

/**
 * 设备Controller
 * 
 * @author sdb
 * @date 2021-10-20
 */
@RestController
@RequestMapping("/deviceManager/device")
public class sdbIotDeviceController extends BaseController
{
    @Autowired
    private IsdbIotDeviceService sdbIotDeviceService;

    /**
     * 查询设备列表
     */
    @PreAuthorize("@ss.hasPermi('deviceManager:device:list')")
    @GetMapping("/list")
    public TableDataInfo list(sdbIotDevice sdbIotDevice)
    {
        startPage();
        List<sdbIotDevice> list = sdbIotDeviceService.selectsdbIotDeviceList(sdbIotDevice);
        return getDataTable(list);
    }

    /**
     * 导出设备列表
     */
    @PreAuthorize("@ss.hasPermi('deviceManager:device:export')")
    @Log(title = "设备", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(sdbIotDevice sdbIotDevice)
    {
        List<sdbIotDevice> list = sdbIotDeviceService.selectsdbIotDeviceList(sdbIotDevice);
        ExcelUtil<sdbIotDevice> util = new ExcelUtil<sdbIotDevice>(sdbIotDevice.class);
        return util.exportExcel(list, "设备数据");
    }

    /**
     * 获取设备详细信息
     */
    @PreAuthorize("@ss.hasPermi('deviceManager:device:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sdbIotDeviceService.selectsdbIotDeviceById(id));
    }

    /**
     * 新增设备
     */
    @PreAuthorize("@ss.hasPermi('deviceManager:device:add')")
    @Log(title = "设备", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody sdbIotDevice sdbIotDevice)
    {
        return toAjax(sdbIotDeviceService.insertsdbIotDevice(sdbIotDevice));
    }

    /**
     * 修改设备
     */
    @PreAuthorize("@ss.hasPermi('deviceManager:device:edit')")
    @Log(title = "设备", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody sdbIotDevice sdbIotDevice)
    {
        return toAjax(sdbIotDeviceService.updatesdbIotDevice(sdbIotDevice));
    }

    /**
     * 删除设备
     */
    @PreAuthorize("@ss.hasPermi('deviceManager:device:remove')")
    @Log(title = "设备", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sdbIotDeviceService.deletesdbIotDeviceByIds(ids));
    }
}
