// App.jsx - Root component với routing và Auth Context
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { ThemeProvider } from './context/ThemeContext';
import { AuthProvider } from './auth/AuthContext';
import MainLayout from './layouts/MainLayout';
import { ToastContainer } from './components/shared';
import ProtectedRoute from './auth/ProtectedRoute';

// Pages
import Dashboard from './pages/Dashboard';
import Products from './pages/Products';
import Categories from './pages/Categories';
import Brands from './pages/Brands';
import Customers from './pages/Customers';
import Suppliers from './pages/Suppliers';
import Employees from './pages/Employees';
import Orders from './pages/Orders';
import POS from './pages/POS';
import Stock from './pages/Stock';
import StockImport from './pages/StockImport';
import StockExport from './pages/StockExport';
import Reports from './pages/Reports';
import Settings from './pages/Settings';
import Login from './pages/Login';
import Register from './pages/Register';
import Account from './pages/Account';
import Tax from './pages/Tax';

export default function App() {
  return (
    <ThemeProvider>
      <AuthProvider>
        <BrowserRouter>
          <Routes>
            {/* Public routes outside MainLayout */}
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />

            {/* Private routes inside MainLayout */}
            <Route
              path="/*"
              element={
                <ProtectedRoute>
                  <MainLayout>
                    <Routes>
                      {/* ADMIN only */}
                      <Route path="/" element={<ProtectedRoute roles={['ADMIN']}><Dashboard /></ProtectedRoute>} />
                      <Route path="/settings" element={<ProtectedRoute roles={['ADMIN']}><Settings /></ProtectedRoute>} />
                      
                      {/* BRANCH_MANAGER only */}
                      <Route path="/branch-dashboard" element={<ProtectedRoute roles={['BRANCH_MANAGER']}><Dashboard /></ProtectedRoute>} />
                      
                      {/* Products & Warehouse (ADMIN, BRANCH_MANAGER, INVENTORY_STAFF) */}
                      <Route path="/products" element={<ProtectedRoute roles={['ADMIN', 'BRANCH_MANAGER', 'INVENTORY_STAFF']}><Products /></ProtectedRoute>} />
                      <Route path="/categories" element={<ProtectedRoute roles={['ADMIN', 'BRANCH_MANAGER', 'INVENTORY_STAFF']}><Categories /></ProtectedRoute>} />
                      <Route path="/brands" element={<ProtectedRoute roles={['ADMIN', 'BRANCH_MANAGER', 'INVENTORY_STAFF']}><Brands /></ProtectedRoute>} />
                      <Route path="/stock" element={<ProtectedRoute roles={['ADMIN', 'BRANCH_MANAGER', 'INVENTORY_STAFF']}><Stock /></ProtectedRoute>} />
                      <Route path="/stock/import" element={<ProtectedRoute roles={['ADMIN', 'BRANCH_MANAGER', 'INVENTORY_STAFF']}><StockImport /></ProtectedRoute>} />
                      <Route path="/stock/export" element={<ProtectedRoute roles={['ADMIN', 'BRANCH_MANAGER', 'INVENTORY_STAFF']}><StockExport /></ProtectedRoute>} />
                      <Route path="/inventory" element={<ProtectedRoute roles={['ADMIN', 'BRANCH_MANAGER', 'INVENTORY_STAFF']}><Stock /></ProtectedRoute>} />
                      
                      {/* Customers & Suppliers (ADMIN, BRANCH_MANAGER) */}
                      <Route path="/customers" element={<ProtectedRoute roles={['ADMIN', 'BRANCH_MANAGER']}><Customers /></ProtectedRoute>} />
                      <Route path="/suppliers" element={<ProtectedRoute roles={['ADMIN', 'BRANCH_MANAGER']}><Suppliers /></ProtectedRoute>} />
                      
                      {/* Employees & Reports (ADMIN, BRANCH_MANAGER) */}
                      <Route path="/employees" element={<ProtectedRoute roles={['ADMIN', 'BRANCH_MANAGER']}><Employees /></ProtectedRoute>} />
                      <Route path="/reports" element={<ProtectedRoute roles={['ADMIN', 'BRANCH_MANAGER']}><Reports /></ProtectedRoute>} />
                      
                      {/* Orders & POS (ADMIN, CASHIER or BRANCH_MANAGER for orders) */}
                      <Route path="/orders" element={<ProtectedRoute roles={['ADMIN', 'BRANCH_MANAGER', 'CASHIER']}><Orders /></ProtectedRoute>} />
                      <Route path="/pos" element={<ProtectedRoute roles={['ADMIN', 'CASHIER']}><POS /></ProtectedRoute>} />
                      
                      {/* Tax & Finance (ADMIN, ACCOUNTANT) */}
                      <Route path="/tax" element={<ProtectedRoute roles={['ADMIN', 'ACCOUNTANT']}><Tax /></ProtectedRoute>} />
                      <Route path="/finance" element={<ProtectedRoute roles={['ADMIN', 'ACCOUNTANT']}><Tax /></ProtectedRoute>} />
                      
                      {/* CUSTOMER only */}
                      <Route path="/customer" element={<ProtectedRoute roles={['CUSTOMER']}><Account /></ProtectedRoute>} />

                      {/* Shared profile and account settings (All roles can access) */}
                      <Route path="/account" element={<ProtectedRoute><Account /></ProtectedRoute>} />
                      <Route path="/profile" element={<ProtectedRoute><Account /></ProtectedRoute>} />
                    </Routes>
                  </MainLayout>
                </ProtectedRoute>
              }
            />
          </Routes>
          <ToastContainer />
        </BrowserRouter>
      </AuthProvider>
    </ThemeProvider>
  );
}


