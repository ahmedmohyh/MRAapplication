<#include "header.ftl">
<div class="x-content-wrapper" style="height: 862px;">
   <section class="content-header">
      <div class="container-fluid">
         <div class="row mb-2">
            <div class="col-sm-6 ">
               <h3>Welcome to our All Movies Page</h3>
               <p>This is just for testing purposes </p>
            </div>
            <div class="col-sm-6">
            </div>
         </div>
      </div>
   </section>
   <!-- Main content -->
   <section class="content">
      <div class="container-fluid">
         <table id="tblMovies" class="x-table table table-sm table-hover table-bordered table-striped searchable mt-2">
            <thead>
               <tr>
                  <th width="20%" style="text-align: center">Title</th>
                  <th width="20%" style="text-align: center">Director</th>
                  <th width="20%" style="text-align: center">Publishing Date</th>
                  <th width="20%" style="text-align: center">Actors</th>
                  <th width="20%" style="text-align: center">Rate Movie</th>
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
                  <td style="text-align: center">
                     <form action="/RateMovie?FilmID= ${movieList.getId()}" method="POST">
                        <input type="submit" value="Rate" class="btn btn-block btn-primary"/>
                     </form>
                  </td>
               </tr>
               </#list>
            </tbody>
         </table>
      </div>
   </section>
</div>
<#include "footer.ftl">