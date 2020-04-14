# check-out-total
## <a href="https://github.com/PillarTechnology/kata-checkout-order-total"> check-out-total-kata description</a>
Implemented all the use cases. And passed the final test.<br><br>
Sample products' info is hard coded in the CheckOut file. <br>
<table>
<tr>     <th>Product Name</th> <th>Price</th> <th>Mark Down</th> <th>On Sale Special</th> </tr>
<tr>     <td>Soup</td>  <td>2.56</td>  <td>0.3</td>  <td></td> </tr>
<tr>     <td>Banana</td>  <td>0.6</td>  <td>0</td>  <td>Buy 3 pounds get 2 pounds or less at 50% off</td> </tr>
<tr>     <td>Milk</td>  <td>4.2</td>  <td>0</td>  <td>Buy 3 get 1 free limit 8</td> </tr>
<tr>     <td>Egg</td>  <td>3.9</td>  <td>0.9</td>  <td>Buy 4 get 2 at 50%off </td> </tr>
<tr>     <td>Pasta</td>  <td>1.2</td>  <td>0</td>  <td>Buy 3 for $3</td> </tr>
<tr>     <td>Rice</td>  <td>9.99</td>  <td>0</td>  <td>Buy 3 for $20</td> </tr>
</table>
There is a test file in the main/src folder. If you want to create a new one, please follow the format as below. <br><br>
Except the weighted items, others' quantity can only be one at a time.<br>
(If needed, I can modify the method so it can take different numbers.)<br><br>
Here is some sample input of the test file:<br>
<pre>
scan soup 1 <br>
void rice 1 <br>
scan pasta 1 <br>
scan  egg 1 <br>
void  milk 1 <br>
scan  rice 1 <br>
scan  banana 2.6 <br>
void  soup 1 <br>
void  pasta 1 <br>
void  egg 1 <br>
void  rice 1 <br>
</pre>
You can simply test it by compling the java files and pass the path of the test file through the command line.
All the logic functions are in CheckOut.java. The main method is in CheckOutTotal.java<br><br>
You can clone this repo first, but only the source folder is needed.
<pre>git clone https://github.com/Spongebobgao/check-out-total.git</pre>
<pre>cd check-out-total/main</pre>
Then compile and run:
<pre>javac src/SaleType.java src/Special.java src/Product.java src/AllProductsInStore 
src/ScanOnSaleProduct src/VoidOnSaleProduct src/CheckOut.java src/Main.java</pre>
<pre>java src/Main</pre>
When you see the prompt, please enter the file path to test the scan and void method.
