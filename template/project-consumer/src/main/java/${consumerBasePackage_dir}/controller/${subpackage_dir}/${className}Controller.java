<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign shortName = table.shortName>
<#list table.columns as column>
<#if column.pk>
<#assign pk = column.columnName>
<#assign pkType = column.simpleJavaType>
</#if>
</#list>

package ${consumerBasePackage}.controller.${subpackage};

import java.util.List;

import ${apiBasePackage}.dubbo.${subpackage}.${className}Provider;
import ${apiBasePackage}.model.${subpackage}.${className};

import ${consumerBasePackage}.model.query.${subpackage}.${className}Query;
import ${consumerBasePackage}.model.request.${subpackage}.${className}Request;
import ${consumerBasePackage}.model.response.${subpackage}.${className}Response;

import ${commonBasePackage}.enums.ResponseCodeEnum;
import ${commonBasePackage}.model.PageResultModel;
import ${commonBasePackage}.model.ResponseJson;
import ${commonBasePackage}.util.BeanCopierUtil;
import ${commonBasePackage}.util.StringUtil;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@Api(value= "${className}Controller",tags ="${table.tableAlias}" ,description="${table.tableAlias}相关功能接口API")
@RestController
public class ${className}Controller {
    
    @Reference
    private ${className}Provider ${classNameLower}Provider;
    
    @ApiOperation(value = "新增" ,notes="新增数据记录")
    @PostMapping(path = "/${classNameLower}/add")
    public ResponseJson<Object> add${className}(
            @ApiParam("请求参数") @RequestBody @Validated(${className}Request.Add.class) ${className}Request  ${classNameLower}Request){
        
        ${className} ${classNameLower} = BeanCopierUtil.invoke(${classNameLower}Request, ${className}.class);
        <#if pkType == "String">
        ${classNameLower}.set${pk}(StringUtil.uuid());
        </#if>
        ${classNameLower}Provider.insertSelective(${classNameLower});
        
        return new ResponseJson<Object>(true, ResponseCodeEnum.SUCCESS);
    }
    
    @ApiOperation(value = "修改" ,notes="修改数据记录")
    @PutMapping(path = "/${classNameLower}/update/{id}")
    public ResponseJson<Object> update${className}(
            @ApiParam("记录id") @PathVariable("id") ${pkType} id ,
            @ApiParam("请求参数") @RequestBody @Validated(${className}Request.Edit.class) ${className}Request  ${classNameLower}Request){
        
        ${className} ${classNameLower} = BeanCopierUtil.invoke(${classNameLower}Request, ${className}.class);
        ${classNameLower}.set${pk}(id);
        
        ${classNameLower}Provider.updateByPrimaryKeySelective(${classNameLower});
        
        return new ResponseJson<Object>(true, ResponseCodeEnum.SUCCESS);
    }
    
    @ApiOperation(value = "删除" ,notes="删除数据记录")
    @DeleteMapping(path = "/${classNameLower}/delete/{id}")
    public ResponseJson<Object> delete${className}(@ApiParam("记录id") @PathVariable("id") String id) {
        
        ${classNameLower}Provider.deleteByPrimaryKey(id);
        
        return new ResponseJson<Object>(true, ResponseCodeEnum.SUCCESS);
    }
    
    @ApiOperation(value = "查询" ,notes="根据id，查询相应数据记录")
    @GetMapping(path = "/${classNameLower}/get/{id}")
    public ResponseJson<${className}Response> get${className}(@ApiParam("记录id") @PathVariable("id") String id){
        
        ${className} ${classNameLower} = ${classNameLower}Provider.findByPrimaryKey(id);
        
        ${className}Response ${classNameLower}Response = BeanCopierUtil.invoke(${classNameLower}, ${className}Response.class);
        
        return new ResponseJson<${className}Response>(true, ResponseCodeEnum.SUCCESS, ${classNameLower}Response);
    }
    
    @ApiOperation(value = "查询" ,notes="查询相应数据记录，分页显示")
    @GetMapping(path = "/${classNameLower}/listPage")
    public ResponseJson<List<${className}Response>> listPage${className}(@ApiParam("请求数据") ${className}Query ${classNameLower}Query){
        ${className} ${classNameLower} = BeanCopierUtil.invoke(${classNameLower}Query, ${className}.class);
        
        PageResultModel<${className}> pageResult = ${classNameLower}Provider.selectWithPage(${classNameLower}, ${classNameLower}Query.getPageNum(), ${classNameLower}Query.getPageSize());
        
        List<${className}Response> ${classNameLower}Responses = BeanCopierUtil.invokeList(pageResult.getDataList(), ${className}Response.class);
        
        return new ResponseJson<List<${className}Response>>(true, ResponseCodeEnum.SUCCESS, ${classNameLower}Responses,pageResult.getTotal());
    }
}
