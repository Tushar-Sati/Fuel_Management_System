// Simple script to add fuel records to the table
document.getElementById('fuel-form').addEventListener('submit', function(event) {
    event.preventDefault();

    // Get form values
    const vehicleId = document.getElementById('vehicle-id').value;
    const fuelAmount = document.getElementById('fuel-amount').value;
    const fuelPrice = document.getElementById('fuel-price').value;
    const transactionDate = document.getElementById('transaction-date').value;

    // Calculate total cost
    const totalCost = (fuelAmount * fuelPrice).toFixed(2);

    // Add a new row to the table
    const newRow = document.createElement('tr');
    newRow.innerHTML = `
        <td>${vehicleId}</td>
        <td>${fuelAmount}</td>
        <td>${fuelPrice}</td>
        <td>${totalCost}</td>
        <td>${transactionDate}</td>
    `;
    
    document.getElementById('records-table').appendChild(newRow);

    // Clear form
    document.getElementById('fuel-form').reset();
});