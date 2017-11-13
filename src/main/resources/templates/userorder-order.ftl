<div class="form-group">
    <label style="color:white" for="restaurant">Restaurant</label>
    <div class="col-md-7">
        <select size="6" name="restaurant" data-toggle="dropdown" ng-model="octrl.selectedRestaurant"  ng-options="restaurant.id as restaurant.name for restaurant in octrl.getAllRestaurants() track by restaurant.id" ng-change="octrl.searchProductRestaurantLive(octrl.selectedRestaurant)">
            <option value="">--Select Restaurant--</option>
        </select>
    </div>
</div>
<div class="panel panel-default">
    <!-- Default panel contents -->
    <div class="panel-heading"><span class="lead">List of Product Restaurant</span></div>
    <div class="panel-body">
        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>SELECT</th>
                    <th>ID</th>
                    <th>NAME</th>
                    <th>PRICE</th>
                    <th>QUANTITY</th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="p in octrl.getAllProductRestaurantsLive">
                    <td style="width: 10%;"> <input type="checkbox" ng-model="octrl.ids[p.id]" ng-true-value="{{p.id}}"></td>
                    <td style="width: 10%;">{{p.id}}</td>
                    <td style="width: 25%;">{{p.name}}</td>
                    <td style="width: 10%;">{{p.price}}</td>
                    <td style="width: 10%;"><input type="text" ng-model="p.quantity" id="pquantity" class="username form-control input-sm" placeholder="Enter the quantity" required ng-minlength="1"/> </td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<div class="form-group row">
    <div class="col-xs-6 col-xs-offset-3">
        <button type="submit" ng-disabled="form.$invalid || dataLoading" class="btn btn-block btn-first-step">Confirm</button>
    </div>
</div>