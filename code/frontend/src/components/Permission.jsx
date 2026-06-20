// src/components/Permission.jsx
import { useAuth } from '../auth/AuthContext';

export default function Permission({ roles, children }) {
  const { user } = useAuth();
  
  if (!user || !roles) return null;
  
  const hasPermission = roles.includes(user.role);
  
  if (!hasPermission) return null;
  
  return <>{children}</>;
}
