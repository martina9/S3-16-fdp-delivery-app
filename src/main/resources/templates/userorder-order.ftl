<div class="form-group">
    <label for="restaurant">Restaurant</label>
    <div class="col-md-7">
        <select size="6" name="restaurant" data-toggle="dropdown" ng-model="octrl.selectedRestaurant"  ng-options="restaurant.id as restaurant.name for restaurant in octrl.getAllRestaurants() track by restaurant.id" ng-change="octrl.searchProductRestaurant(octrl.selectedRestaurant)">
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
                <tr ng-repeat="p in octrl.getAllProductRestaurants()">
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
                    <td><button type="button" ng-click="octrl.confirmOrder()" class="btn btn-danger custom-width">Confirm Order</button></td>
                </tr>
                </tbody>
            </table>
            <span ng-repeat='id in octrl.ids'>{{id}}</span></td>
        </div>
    </div>
</div>

<#--<div class="form-actions">-->
    <#--<button type="submit" ng-disabled="form.$invalid || dataLoading" class="btn btn-danger">Registration</button>-->
    <#--<img ng-if="dataLoading" src="data:image/gif;base64,R0lGODlhEAAQAPIAAP///wAAAMLCwkJCQgAAAGJiYoKCgpKSkiH/C05FVFNDQVBFMi4wAwEAAAAh/hpDcmVhdGVkIHdpdGggYWpheGxvYWQuaW5mbwAh+QQJCgAAACwAAAAAEAAQAAADMwi63P4wyklrE2MIOggZnAdOmGYJRbExwroUmcG2LmDEwnHQLVsYOd2mBzkYDAdKa+dIAAAh+QQJCgAAACwAAAAAEAAQAAADNAi63P5OjCEgG4QMu7DmikRxQlFUYDEZIGBMRVsaqHwctXXf7WEYB4Ag1xjihkMZsiUkKhIAIfkECQoAAAAsAAAAABAAEAAAAzYIujIjK8pByJDMlFYvBoVjHA70GU7xSUJhmKtwHPAKzLO9HMaoKwJZ7Rf8AYPDDzKpZBqfvwQAIfkECQoAAAAsAAAAABAAEAAAAzMIumIlK8oyhpHsnFZfhYumCYUhDAQxRIdhHBGqRoKw0R8DYlJd8z0fMDgsGo/IpHI5TAAAIfkECQoAAAAsAAAAABAAEAAAAzIIunInK0rnZBTwGPNMgQwmdsNgXGJUlIWEuR5oWUIpz8pAEAMe6TwfwyYsGo/IpFKSAAAh+QQJCgAAACwAAAAAEAAQAAADMwi6IMKQORfjdOe82p4wGccc4CEuQradylesojEMBgsUc2G7sDX3lQGBMLAJibufbSlKAAAh+QQJCgAAACwAAAAAEAAQAAADMgi63P7wCRHZnFVdmgHu2nFwlWCI3WGc3TSWhUFGxTAUkGCbtgENBMJAEJsxgMLWzpEAACH5BAkKAAAALAAAAAAQABAAAAMyCLrc/jDKSatlQtScKdceCAjDII7HcQ4EMTCpyrCuUBjCYRgHVtqlAiB1YhiCnlsRkAAAOwAAAAAAAAAAAA==" />-->
<#--</div>-->

<div class="form-group row">
    <div class="col-xs-6 col-xs-offset-3">
        <a ui-sref="userorder.order" class="btn btn-block btn-first-step">
            Confirm order <span class="glyphicon glyphicon-circle-arrow-right"></span>
        </a>
    </div>
</div>