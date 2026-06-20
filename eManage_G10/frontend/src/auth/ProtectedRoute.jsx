// src/auth/ProtectedRoute.jsx
import { Navigate, useNavigate } from 'react-router-dom';
import { useAuth } from './AuthContext';
import { ShieldAlert, LogOut, ArrowLeft } from 'lucide-react';
import { ROLE_LABELS } from './permissions';

export function getRedirectPath(role) {
  switch (role) {
    case 'ADMIN':
      return '/';
    case 'BRANCH_MANAGER':
      return '/branch-dashboard';
    case 'CASHIER':
      return '/pos';
    case 'INVENTORY_STAFF':
      return '/inventory';
    case 'ACCOUNTANT':
      return '/finance';
    case 'CUSTOMER':
      return '/customer';
    default:
      return '/';
  }
}

export default function ProtectedRoute({ children, roles }) {
  const { user, isAuthenticated, loading, logout } = useAuth();
  const navigate = useNavigate();

  if (loading) {
    return (
      <div className="min-h-screen flex items-center justify-center bg-surface-50 dark:bg-gray-950">
        <div className="flex flex-col items-center gap-3">
          <div className="w-10 h-10 border-4 border-primary-500 border-t-transparent rounded-full animate-spin"></div>
          <p className="text-sm font-semibold text-gray-500 dark:text-gray-400">Đang tải...</p>
        </div>
      </div>
    );
  }

  if (!isAuthenticated) {
    return <Navigate to="/login" replace />;
  }

  // Check if roles are specified and if the user's role is permitted
  if (roles && roles.length > 0 && !roles.includes(user?.role)) {
    const homePath = getRedirectPath(user?.role);
    
    return (
      <div className="min-h-screen flex items-center justify-center bg-surface-50 dark:bg-gray-950 p-6">
        <div className="max-w-md w-full bg-white dark:bg-gray-900 rounded-2xl shadow-xl border border-gray-100 dark:border-gray-800/80 p-8 text-center animate-fadeIn">
          <div className="w-16 h-16 bg-red-50 dark:bg-red-950/20 border border-red-100 dark:border-red-900/30 rounded-2xl flex items-center justify-center mx-auto text-red-500 mb-6 shadow-sm">
            <ShieldAlert size={32} />
          </div>
          
          <h1 className="text-2xl font-black text-gray-900 dark:text-white mb-2">403 Forbidden</h1>
          <p className="text-sm text-gray-500 dark:text-gray-400 font-semibold mb-6">
            Tài khoản của bạn ({user?.name} - <span className="text-red-500">{ROLE_LABELS[user?.role] || user?.role}</span>) không có quyền truy cập vào chức năng/trang này.
          </p>

          <div className="space-y-3">
            <button
              onClick={() => navigate(homePath)}
              className="w-full btn btn-primary py-3 rounded-xl justify-center font-bold text-xs"
            >
              <ArrowLeft size={16} className="mr-2" />
              Về trang chủ của bạn
            </button>
            
            <button
              onClick={() => {
                logout();
                navigate('/login');
              }}
              className="w-full flex items-center justify-center gap-2 py-3 rounded-xl border border-gray-200 dark:border-gray-800 text-xs font-bold text-gray-700 dark:text-gray-300 hover:bg-gray-50 dark:hover:bg-gray-800/50 transition-colors"
            >
              <LogOut size={16} className="text-gray-400" />
              Đăng xuất & Đổi tài khoản
            </button>
          </div>
        </div>
      </div>
    );
  }

  return children;
}
