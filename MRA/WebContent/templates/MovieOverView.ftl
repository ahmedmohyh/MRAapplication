<#include "header.ftl">

<b>Welcome to our All Movies Page</b><br><br>
<b>This is just for testing purposes</b>

<table id="tblMovies" class="x-table table table-sm table-hover table-bordered table-striped searchable mt-2">
   <thead>
      <tr>
         <th width="25%">Title</th>
         <th width="25%">Director</th>
         <th width="25%">Publishing Date</th>
         <th width="25%">Actors</th>
      </tr>
   </thead>
   <tbody>
   <#list movies as movieList >
   <tr role="row">
   <td style="text-align: center">
   <span>
     ${movieList.getTitle()}
   </span>
    </td>
    <td style="text-align: center">
   <span>
     ${movieList.getDirecotr()}
   </span>
    </td>
    <td style="text-align: center">
   <span>
     ${movieList.getOriginalPublishingDate()}
   </span>
    </td>
    <td style="text-align: center">
   <span>
     ${movieList.getAcotrs()}
   </span>
    </td>
   </tr>
   </#list>
   </tbody>
</table>

<#include "footer.ftl">