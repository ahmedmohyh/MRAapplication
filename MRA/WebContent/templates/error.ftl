<#include "header.ftl">

<#if value = false>
<div class="error">${message}</div>
<#else>
<div class="success">${message}</div>
</#if>

<#include "footer.ftl">