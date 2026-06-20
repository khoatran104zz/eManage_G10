import { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { Zap, Mail, Loader2, AlertCircle } from 'lucide-react';
import { AuthLayout, AuthCard, PasswordInput, toast } from '../components/shared';
import { useAuth } from '../auth/AuthContext';
import { getRedirectPath } from '../auth/ProtectedRoute';

export default function Login() {
  const navigate = useNavigate();
  const { login } = useAuth();
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [rememberMe, setRememberMe] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState('');

  const quickRoles = [
    { role: 'ADMIN', name: 'Lê Văn Admin', label: 'Admin / Owner', email: 'admin@emanage.vn', branchId: null },
    { role: 'BRANCH_MANAGER', name: 'Lê Văn Manager', label: 'Quản lý Chi nhánh', email: 'manager@emanage.vn', branchId: 'b-trungtam' },
    { role: 'CASHIER', name: 'Phạm Thị Cashier', label: 'Bán hàng / POS', email: 'cashier@emanage.vn', branchId: 'b-trungtam' },
    { role: 'INVENTORY_STAFF', name: 'Trần Văn Kho', label: 'Thủ kho', email: 'inventory@emanage.vn', branchId: 'b-trungtam' },
    { role: 'ACCOUNTANT', name: 'Hoàng Thị Kế Toán', label: 'Kế toán', email: 'accountant@emanage.vn', branchId: null },
    { role: 'CUSTOMER', name: 'Khách Hàng A', label: 'Khách hàng', email: 'customer@gmail.com', branchId: null },
  ];

  const handleQuickLogin = (qr) => {
    toast(`Đăng nhập với vai trò ${qr.label}`);
    const userData = {
      id: 'u-' + qr.role.toLowerCase(),
      name: qr.name,
      email: qr.email,
      role: qr.role,
      branchId: qr.branchId,
    };
    login(userData);
    navigate(getRedirectPath(qr.role));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');

    if (!email) {
      setError('Vui lòng nhập địa chỉ email');
      return;
    }
    if (!password) {
      setError('Vui lòng nhập mật khẩu');
      return;
    }

    setIsLoading(true);
    try {
      // Giả lập gọi API đăng nhập 1.2s
      await new Promise((resolve) => setTimeout(resolve, 1200));

      if (email.includes('@') && password.length >= 6) {
        const defaultUsers = [
          { id: 'u-admin', name: 'Lê Văn Admin', email: 'admin@emanage.vn', role: 'ADMIN', branchId: null },
          { id: 'u-manager', name: 'Lê Văn Manager', email: 'manager@emanage.vn', role: 'BRANCH_MANAGER', branchId: 'b-trungtam' },
          { id: 'u-cashier', name: 'Phạm Thị Cashier', email: 'cashier@emanage.vn', role: 'CASHIER', branchId: 'b-trungtam' },
          { id: 'u-inventory', name: 'Trần Văn Kho', email: 'inventory@emanage.vn', role: 'INVENTORY_STAFF', branchId: 'b-trungtam' },
          { id: 'u-accountant', name: 'Hoàng Thị Kế Toán', email: 'accountant@emanage.vn', role: 'ACCOUNTANT', branchId: null },
          { id: 'u-customer', name: 'Khách Hàng A', email: 'customer@gmail.com', role: 'CUSTOMER', branchId: null }
        ];

        let systemUsers = defaultUsers;
        try {
          const stored = localStorage.getItem('system_users');
          if (stored) {
            systemUsers = JSON.parse(stored);
          } else {
            localStorage.setItem('system_users', JSON.stringify(defaultUsers));
          }
        } catch (e) {
          systemUsers = defaultUsers;
        }

        const matchedUser = systemUsers.find(u => u.email.toLowerCase() === email.toLowerCase());

        let userData;
        if (matchedUser) {
          userData = { ...matchedUser };
        } else {
          let role = 'ADMIN';
          let name = 'Lê Văn Admin';
          let branchId = null;

          const emailLower = email.toLowerCase();
          if (emailLower.includes('manager')) {
            role = 'BRANCH_MANAGER';
            name = 'Lê Văn Manager';
            branchId = 'b-trungtam';
          } else if (emailLower.includes('cashier') || emailLower.includes('sales')) {
            role = 'CASHIER';
            name = 'Phạm Thị Cashier';
            branchId = 'b-trungtam';
          } else if (emailLower.includes('inventory') || emailLower.includes('kho')) {
            role = 'INVENTORY_STAFF';
            name = 'Trần Văn Kho';
            branchId = 'b-trungtam';
          } else if (emailLower.includes('accountant') || emailLower.includes('ketoan')) {
            role = 'ACCOUNTANT';
            name = 'Hoàng Thị Kế Toán';
          } else if (emailLower.includes('customer') || emailLower.includes('khachhang')) {
            role = 'CUSTOMER';
            name = 'Khách Hàng A';
          }

          userData = {
            id: 'u-' + role.toLowerCase() + '-' + Math.random().toString(36).substr(2, 5),
            name,
            email,
            role,
            branchId,
          };
        }

        toast('Đăng nhập hệ thống thành công');
        login(userData);
        navigate(getRedirectPath(userData.role));
      } else {
        throw new Error('Email hoặc mật khẩu không chính xác (mật khẩu phải từ 6 ký tự)');
      }
    } catch (err) {
      setError(err.message || 'Đăng nhập thất bại. Vui lòng thử lại.');
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <AuthLayout>
      <div className="flex flex-col items-center mb-6">
        {/* Logo */}
        <div className="w-12 h-12 rounded-2xl header-gradient flex items-center justify-center shadow-lg shadow-primary-500/20 mb-3">
          <Zap size={24} className="text-white" fill="white" />
        </div>
        <div className="leading-tight text-center">
          <span className="font-extrabold text-gray-900 dark:text-white text-2xl tracking-tight">
            e<span className="text-primary-500">Manage</span>
          </span>
          <p className="text-xs text-gray-400 dark:text-gray-500 font-medium mt-1">Hệ thống quản lý bán hàng chuyên nghiệp</p>
        </div>
      </div>

      <AuthCard>
        <div className="mb-6">
          <h2 className="text-xl font-bold text-gray-900 dark:text-white">Đăng nhập hệ thống</h2>
          <p className="text-xs text-gray-500 dark:text-gray-400 mt-1">Quản lý cửa hàng của bạn dễ dàng hơn</p>
        </div>

        {/* Error Alert */}
        {error && (
          <div className="mb-4 p-3.5 bg-red-50 dark:bg-red-950/20 border border-red-100 dark:border-red-900/30 rounded-xl text-xs text-red-600 dark:text-red-400 flex items-start gap-2.5 animate-fadeIn">
            <AlertCircle size={15} className="shrink-0 mt-0.5" />
            <span>{error}</span>
          </div>
        )}

        <form onSubmit={handleSubmit} className="space-y-4">
          {/* Email field */}
          <div>
            <label className="label">Email</label>
            <div className="relative">
              <span className="absolute left-3.5 top-1/2 -translate-y-1/2 text-gray-400 dark:text-gray-500">
                <Mail size={16} />
              </span>
              <input
                type="email"
                className={`input pl-10 focus:ring-primary-500/40 focus:border-primary-500 ${
                  error && !email ? 'border-red-500 focus:border-red-500 focus:ring-red-500/20' : ''
                }`}
                placeholder="ten@email.com"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                disabled={isLoading}
              />
            </div>
          </div>

          {/* Password field */}
          <div>
            <div className="flex items-center justify-between mb-1.5">
              <label className="label mb-0">Mật khẩu</label>
              <Link
                to="/forgot-password"
                className="text-xs font-semibold text-primary-500 hover:text-primary-600 transition-colors"
                onClick={(e) => {
                  e.preventDefault();
                  toast('Chức năng khôi phục mật khẩu đang được phát triển', 'error');
                }}
              >
                Quên mật khẩu?
              </Link>
            </div>
            <PasswordInput
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              placeholder="Nhập mật khẩu"
              disabled={isLoading}
              error={error && !password}
            />
          </div>

          {/* Remember me */}
          <div className="flex items-center">
            <input
              id="remember-me"
              type="checkbox"
              className="h-4 w-4 rounded border-gray-300 dark:border-gray-700 text-primary-500 focus:ring-primary-500 bg-white dark:bg-gray-800 transition-colors cursor-pointer"
              checked={rememberMe}
              onChange={(e) => setRememberMe(e.target.checked)}
              disabled={isLoading}
            />
            <label
              htmlFor="remember-me"
              className="ml-2 block text-xs font-semibold text-gray-600 dark:text-gray-400 cursor-pointer select-none"
            >
              Ghi nhớ đăng nhập
            </label>
          </div>

          {/* Submit button */}
          <button
            type="submit"
            className="w-full btn btn-primary py-3 rounded-xl justify-center font-bold text-sm select-none"
            disabled={isLoading}
          >
            {isLoading ? (
              <>
                <Loader2 size={16} className="animate-spin mr-2" />
                Đang kết nối...
              </>
            ) : (
              'Đăng nhập'
            )}
          </button>
        </form>

        {/* Quick Login Demo Section */}
        <div className="mt-5 pt-4 border-t border-gray-100 dark:border-gray-800">
          <p className="text-xs font-semibold text-gray-500 dark:text-gray-400 mb-2 text-center">Đăng nhập nhanh để kiểm tra phân quyền (RBAC):</p>
          <div className="grid grid-cols-2 gap-2">
            {quickRoles.map((qr) => (
              <button
                key={qr.role}
                type="button"
                onClick={() => handleQuickLogin(qr)}
                className="py-1.5 px-2 bg-gray-50 dark:bg-gray-800/40 border border-gray-100 dark:border-gray-800 hover:bg-primary-50 dark:hover:bg-primary-950/20 hover:border-primary-200 dark:hover:border-primary-800/50 rounded-xl transition-all text-left flex flex-col justify-between"
              >
                <span className="text-[10px] font-bold text-primary-600 dark:text-primary-400 truncate">{qr.name}</span>
                <span className="text-[8px] text-gray-400 dark:text-gray-500 font-semibold">{qr.label}</span>
              </button>
            ))}
          </div>
        </div>

        {/* Footer */}
        <div className="mt-6 pt-5 border-t border-gray-100 dark:border-gray-800 text-center">
          <p className="text-xs text-gray-500 dark:text-gray-400 font-semibold">
            Chưa có tài khoản?{' '}
            <Link
              to="/register"
              className="text-primary-500 hover:text-primary-600 transition-colors font-bold"
            >
              Đăng ký ngay
            </Link>
          </p>
        </div>
      </AuthCard>
    </AuthLayout>
  );
}

