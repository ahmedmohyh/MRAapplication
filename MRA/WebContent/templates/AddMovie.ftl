<#include "header.ftl">
     <div class="container py-4">
      <div class="card rounded shadow-sm">
        <div class="card-body">
        <div>
         <h2 class="text mb-4">Add Movie to the Database!</h2>
        </div>
          <!-- Form is used for items that will take input from the user -->
          <form name="addingMovieForm" method="POST" action="add_movie">

            <!-- Inputting title of the movie -->
            <div class="form-group row mb-2">
                <p class="col-sm-2 mb-2">Title :</p>
                <div class="p-0 col-sm-6 mb-2">
                    <input type="text" name="title" class="form-control mb-2" required/>
                </div>
            </div>
            
            <!-- Inputting director of the movie -->
            <div class="form-group row mb-2">
                <p class="col-sm-2 mb-2">Director :</p>
                <div class="p-0 col-sm-6 mb-2">
                    <input type="text" name="director" class="form-control mb-2" required/>
                </div>
            </div>
            
            <!-- Inputting original publishing date of the movie -->
            <div class="form-group row mb-2">
                <p class="col-sm-2 mb-2"> Original Publishing Date :</p>
                <div class="p-0 col-sm-6 mb-2">
                    <input type="datetime-local" name="originalPublishingDate" class="form-control mb-2" required/>
                </div>
            </div>
            
            <!-- Inputting main actor list of the movie -->
            <div class="form-group row mb-2">
                <p class="col-sm-2 mb-2">Main Actor List :</p>
                <div class="p-0 col-sm-6 mb-2">
                    <input type="text" name="mainActorList" class="form-control mb-2" required/>
                </div>
            </div>
            
            <button type="submit" value="submit" class="btn btn-primary mb-2 d-block float-end" href="add_movie">Add Movie!</button>
          </form>
        </div>
      </div>
    </div>
<#include "footer.ftl">
    
