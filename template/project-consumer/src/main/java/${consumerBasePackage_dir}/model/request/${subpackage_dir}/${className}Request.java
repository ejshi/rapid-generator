<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 

package ${consumerBasePackage}.model.request.${subpackage};

import org.hibernate.validator.constraints.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: ${className}Request 
 * @Description: ${table.tableAlias},数据请求对象 
 * @author ejshi  
 * @date 2018年1月31日 下午11:05:37  
 */
@ApiModel
public class ${className}Request implements java.io.Serializable {
    
    private static final long serialVersionUID = 1L;

	<#list table.columns as column>
	
	<#if !column.nullable>
	<#if column.isStringColumn>
    @NotBlank(groups={Add.class,Edit.class})
    <#else>
    @NotNull(groups={Add.class,Edit.class})
    </#if>
	</#if>
	@Length(max=${column.size}, groups = {Add.class,Edit.class})
	@ApiModelProperty("${column.columnAlias}")
	private ${column.simpleJavaType} ${column.columnNameLower};
	
	</#list>
	
	public interface Add {}
    
    public interface Edit {}
		
	<#list table.columns as column>
	public void set${column.columnName}(${column.simpleJavaType} value) {
		this.${column.columnNameLower} = value;
	}
	
	public ${column.simpleJavaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}
	
	</#list>
}
<#macro generateJavaColumns>
	<#list table.columns as column>
    <@generateBycondition column.sqlName>
	public void set${column.columnName}(${column.simpleJavaType} value) {
		this.${column.columnNameLower} = value;
	}
	
	public ${column.simpleJavaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}
	
	</@generateBycondition>
	</#list>
</#macro>