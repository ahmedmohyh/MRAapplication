<!DOCTYPE html>
<html lang="de">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
      crossorigin="anonymous"
    />
    <title>Fahrt bewerten</title>
    <style>
      body {
        background: #eee;
      }
      .container {
        max-width: 800px;
      }
    </style>
  </head>
  <body>
    <div class="container py-4">
      <div class="card rounded shadow-sm">
        <div class="card-body">
        <div>
         <h2 class="text mb-4">Rate the Movie!</h2>
        </div>
          <!-- Form is used for items that will take input from the user -->
          <form>
            <!-- text review area -->
            <div class="form-group row mb-3">
              <label for="review" class="col-sm-2 col-form-label">Comment:</label>
              <textarea class="col-sm-6" id="review" rows="5"></textarea>
            </div>
            <!-- nummerical rating area -->
            <div class="row">
                <p class="col-sm-2">Rating:</p>
                <div class="p-0 col-sm-6">
                    <input type="number" class="form-control" value="0" min="0" max="10"/>
                </div>
            </div>
            <button type="submit" class="btn btn-danger mb-2 d-block float-end">Rate!</button>
          </form>
        </div>
      </div>
    </div>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
      crossorigin="anonymous"
    ></script>
  </body>
</html>
