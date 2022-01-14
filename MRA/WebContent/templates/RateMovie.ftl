<#include "header.ftl">
     <div class="container py-4">
      <div class="card rounded shadow-sm">
        <div class="card-body">
        <div>
         <h2 class="text mb-4">Rate the Movie!</h2>
        </div>
          <!-- Form is used for items that will take input from the user -->
          <form name="ratingForm" method="POST" action="rate_movie">
          <div class="row">
            <div class="form-group mb-3 col-sm-5 p-0 d-flex offset-sm-2">
            <select class="form-select" aria-label="List of the Movies" name="movieID">
            <option selected>List of the Movies</option>
            <#list listOfMovies as list>
            <option value = "${list.getId()}">${list.getTitle()}</option>
            </#list>
            </select>
            </div>
          </div>
            <!-- nummerical rating area -->
            <div class="form-group row mb-2">
                <p class="col-sm-2 mb-2">Rating:</p>
                <div class="p-0 col-sm-6 mb-2">
                    <input type="number" name="ratingNumber" class="form-control mb-2" value="0" min="0" max="10" required/>
                </div>
            </div>
            <!-- text review area -->
            <div class="form-group row mb-3">
              <label for="review" class="col-sm-2 col-form-label">Comment:</label>
              <textarea class="col-sm-6" id="review" rows="5" name="comment"></textarea>
            </div>
            <button type="submit" value="submit" class="btn btn-primary mb-2 d-block float-end" href="rate_movie">Rate!</button>
          </form>
        </div>
      </div>
    </div>
<#include "footer.ftl">
    
