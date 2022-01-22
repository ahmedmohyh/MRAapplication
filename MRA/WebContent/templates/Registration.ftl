<#include "header.ftl">
     <div class="container py-4">
      <div class="card rounded shadow-sm">
        <div class="card-body">
        <div>
         <h2 class="text mb-4">Register!</h2>
        </div>
          <!-- Form is used for items that will take input from the user -->
          <form id = "registerForm" name="registerForm" method="POST" action="register_user">
          
            <!-- Entering the username -->
            <div class="form-group row mb-2">
                <p class="col-sm-2 mb-2">Username :</p>
                <div class="p-0 col-sm-6 mb-2">
                    <input type="text" name="username" class="form-control mb-2" required/>
                </div>
            </div>
            
            <!-- Entering the email -->
            <div class="form-group row mb-2">
                <p class="col-sm-2 mb-2">Email :</p>
                <div class="p-0 col-sm-6 mb-2">
                    <input type="text" name="email" class="form-control mb-2" required/>
                </div>
            </div>
            
            <!-- Entering age -->
            <div class="form-group row mb-2">
                <p class="col-sm-2 mb-2">Age:</p>
                <div class="p-0 col-sm-6 mb-2">
                    <input type="number" name="age" class="form-control mb-2" value="0" required/>
                </div>
            </div>
            <button type="submit" value="submit" name="registerButton" id="registerButton" class="btn btn-primary mb-2 d-block float-end" href="register_user">Register!</button>
          </form>        
        </div>
      </div>
    </div>
<#include "footer.ftl">