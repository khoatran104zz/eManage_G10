# Cấu trúc Source Code
I. Source tổng
Hãy đưa cả tài liệu và source code vào cùng folder để AI có thêm nhiều tri thức để làm
├── docs/
│   ├── srs.md/             # tài liệu đặc tả phần mềm
│   ├── convention.md/                # quy tắc code
│   ├── backlogs/         # chứa backlog
│   │   ├── sprint1/           # chứa các user story, task
│   │   └── sprint2/       # chứa các user story, task
│   ├── DB-erd/                # thiết kế db
│   └── UI/UX style guideline/              # style front end
│
├── code/            # chứa code của dự án
│   ├── front end/            
│   └── backend/              
│
├── .agent          # chứa các rule, workflow và skill của AI 
├── test          # chứa các file test case 

II. Frontend
1. Next JS
next-frontend/
├── .husky/                 # Thư mục chứa các script chặn commit (pre-commit hook) để đảm bảo code đã chuẩn convention, đáp ứng đúng lint, build không lỗi,... trước khi được đẩy lên
│   └── pre-commit          # Script chạy tsc và eslint trước khi cho phép git commit
│
├── public/                 # Tài nguyên tĩnh không qua build (favicon, robots.txt, ảnh landing page)
│
├── src/
│   ├── assets/             # Tài nguyên tĩnh đi qua build (images, svg, local fonts)
│   ├── app/                # Next.js App Router (Định nghĩa routing gồm layout, page,... Theo Next)
│   ├── components/         # Chứa các component giao diện
│   │   ├── base/           # Các component base tự custom (BaseButton, BaseTable...)
│   │   └── features/       # Component theo nghiệp vụ
│   ├── lib/                # Cấu hình thư viện (Axios client, utils)
│   ├── hooks/              # Custom React Hooks
│   ├── store/              # Global State (Zustand/Redux)
│   ├── types/              # Định nghĩa TypeScript
│   ├── index.css           # Global CSS (Cấu hình Tailwind)
│   ├── providers/           # Các provider của react
│   ├── schemas/           # Các validator trên front end (Sử dụng zod)
│   ├── services/           # Các hàm gọi api đến backend
│   ├── utils/           # Chứa các hàm sử dụng chung
│   └── constants/              # Định nghĩa các hằng số
│
├── eslint.config.mjs          # Cấu hình linter khắt khe để đảm bảo chất lượng mã nguồn. Cơ bản sẽ phải kiểm tra những điều sau: eslint-plugin-boundaries (để phân chia ranh giới các phần vào đúng folder), áp dụng quy tắc đặt tên của dự án (kebab-case), không hard code hard text hard mã màu, unusedImports, không lạm dụng any, không sử dụng Magic number
├── .prettierrc             # Cấu hình format code chuẩn chung cho cả team
├── tailwind.config.ts      # Cấu hình TailwindCSS, nên ghi đè tailwind bằng ghi đè mã màu
├── tsconfig.json           # Cấu hình TypeScript (Bật Strict Mode)
├── package.json            
├── Dockerfile, .dockerignore, docker compose         # Nếu sử dụng Docker để build
├── Sentry         # Cấu hình để ghi nhận bug trên product
├── .gitignore           # Cấu hình những thứ không push lên git
└── .env             # Cấu hình biến môi trường, có thể tạo thêm các biến thể như .env.local, .env.development,...

III. Back end
2. Clean Architecture 
backend-clean/
├── src/main/java/com/ioc/internship/
│   ├── Application.java
│   │
│   ├── shared/                           # Mã nguồn dùng chéo qua các tầng
│   │   ├── base/                         # BaseDomainEntity, BaseUseCase, BaseController
│   │   ├── exception/                    # Lỗi kỹ thuật (không dính tới nghiệp vụ)
│   │   └── utils/                        # Các helper thuần Java (không chứa thư viện Spring)
│   │
│   ├── domain/                           # TẦNG LÕI
│   │   ├── entity/                       # Plain Java Model
│   │   ├── enums/                        # Các trạng thái nghiệp vụ (UserRole, ProjectStatus)
│   │   ├── exception/                    # Lỗi nghiệp vụ (UserNotFoundException, InvalidScoreException)
│   │   └── repository/                   # CHỈ CÓ INTERFACE (IUserRepository)
│   │
│   ├── application/                      # TẦNG NGHIỆP VỤ (USE CASE)
│   │   ├── dto/                          # Model truyền vào Use Case (Input/Output Boundary)
│   │   ├── usecase/                      # Interface quy định hành động (Ví dụ: CreateUserUseCase)
│   │   └── service/                      # Class implement UseCase. Nó sẽ được "tiêm" IUserRepository vào để làm việc.
│   │
│   ├── infrastructure/                   # TẦNG HẠ TẦNG (KẾT NỐI VỚI THẾ GIỚI BÊN NGOÀI & DB)
│   │   ├── config/                       # Spring Security, Bean Configuration, Kafka/Redis config
│   │   └── persistence/                  # Phần giao tiếp Database
│   │       ├── entity/                   # JPA Entity (Chứa các Annotation @Entity, @Table)
│   │       ├── repository/               # Spring Data JpaRepository
│   │       └── adapter/                  # MẮT XÍCH QUAN TRỌNG: Class implement IUserRepository (ở tầng Domain), gọi xuống JpaRepository để lưu dữ liệu, đồng thời map JPA Entity <-> Domain Entity.
│   │
│   └── presentation/                     # TẦNG HIỂN THỊ (GIAO TIẾP HTTP)
│       ├── controller/                   # Rest Controllers (Nhận HTTP, gọi xuống tầng Application/UseCase)
│       ├── payload/                      # HTTP Request Body, JSON Response
│       └── exception/                    # @RestControllerAdvice để map lỗi Domain sang HTTP Status Code (400, 404, 500)
│
│   ├── unitest/                           # Cài thêm unit test để kiểm thử
│   ├── sonarLint/                           # Cài sonarlint để quản lý chất lượng mã nguồn
├── Dockerfile, .dockerignore, docker compose         # Nếu sử dụng Docker để build
└── resources/
    ├── application.yml
    ├── text/                   # Cấu hình ResourceBundle để lưu text, không hard text vào code
    └── db/migration/                     # Nơi viết các thay đổi DB

# Quy tắc Code
1. Quy tắc đặt tên (Naming)
Backend (Java/Spring Boot)

Package: chữ thường, snake_case cho tên gốc dự án — com.company.sales_management, chia theo layer: config, controller, service, repository, entity, dto, exception.
Class: PascalCase, hậu tố theo vai trò rõ ràng — ProductController, ProductService, ProductRepository, Product (entity), ProductDto (gộp Request/Response làm inner static class trong cùng 1 file).
Field/biến/method: camelCase — costPrice, salePrice, categoryId; getter/setter chuẩn JavaBean (getCostPrice()/setCostPrice(), riêng boolean dùng isActive()).
Cột DB (@Column(name=...)): snake_case — cost_price, created_at, payment_method; tên bảng số nhiều, snake_case — products, order_items.
Hằng số/trạng thái dạng chuỗi: chữ thường — "pending" | "processing" | "completed" | "cancelled" (dùng String + comment liệt kê giá trị hợp lệ ngay trên field, không dùng enum Java).
Exception: hậu tố Exception rõ nghĩa — NotFoundException, BusinessException.
Endpoint REST: /api/{resource-số-nhiều} — /api/products, /api/orders/{id}.

Frontend (React/Vite)

Component file: PascalCase .jsx — Products.jsx, MainLayout.jsx; mỗi page là 1 file trong pages/.
Biến/hàm: camelCase — formatCurrency, getStatusLabel; state setter theo cặp useState chuẩn (products/setProducts).
Module API service: gộp theo resource, hậu tố Api — productsApi, ordersApi, dashboardApi, đặt trong 1 file services/api.js.
Hàm tiện ích định dạng: tiền tố format.../get... — formatCurrency, formatDate, getStatusClass.
Class CSS (Tailwind, dùng lại): kebab-case ngắn gọn — input, btn btn-primary, badge-green.

2. Quy tắc coding chung

Phân tầng rõ ràng: Controller chỉ gọi Service, không chứa business logic; Service xử lý logic + transaction (@Transactional), Repository chỉ truy vấn dữ liệu.
DTO tách biệt Entity: không trả entity trực tiếp ra API; mỗi resource có XxxDto.Request (input có validate bằng @NotBlank...) và XxxDto.Response (output, có factory method from(entity)).
Xử lý lỗi tập trung: toàn bộ exception được bắt ở GlobalExceptionHandler, luôn trả về JSON dạng { "message": "..." } để khớp với interceptor lỗi ở frontend (api.js).
Comment giải thích "tại sao" chứ không lặp lại "cái gì" code đã nói — ví dụ comment business rule (// Soft-delete... (BR-10)) ngay cạnh field liên quan, có tham chiếu mã yêu cầu trong tài liệu SRS.
Soft-delete thay vì xoá cứng với các bản ghi có lịch sử (sản phẩm), dùng cờ active.
Validate input ở cả 2 phía: @Valid + Bean Validation ở backend, kiểm tra cơ bản trước khi gọi API ở frontend.
Trả lỗi/thông báo người dùng bằng tiếng Việt, nhất quán toàn hệ thống (message validate, toast, exception).

3. Quy tắc commit (Conventional Commits)

Cấu trúc: <type>(<scope>): <mô tả ngắn gọn>, theo sau là dòng trống rồi mô tả chi tiết và footer nếu cần.
Mô tả ngắn gọn viết ở thì hiện tại, không viết hoa chữ đầu, không chấm câu cuối câu — ví dụ thêm bộ lọc theo danh mục chứ không phải Đã thêm bộ lọc theo danh mục.
feat: thêm tính năng mới — feat(product): thêm chức năng lọc sản phẩm theo danh mục.
fix: sửa lỗi — fix(order): sửa lỗi tổng tiền sai khi áp dụng giảm giá.
refactor: tái cấu trúc code, không đổi hành vi — refactor(service): tách logic tính tồn kho ra StockService.
style: định dạng code, không ảnh hưởng logic — style(controller): format lại theo chuẩn checkstyle.
docs: thay đổi tài liệu — docs: cập nhật README hướng dẫn cài MySQL.
test: thêm/sửa test — test(product): thêm unit test cho ProductService.
chore: việc lặt vặt không ảnh hưởng code chạy — chore: nâng cấp Spring Boot lên 4.1.0.
perf: cải thiện hiệu năng — perf(dashboard): tối ưu query thống kê doanh thu.
build: thay đổi hệ thống build, CI/CD, pom.xml, package.json — build: thêm maven wrapper.
revert: revert lại một commit trước đó — revert: revert "feat(order): thêm xuất hóa đơn PDF".
Scope gợi ý theo module: product, category, brand, customer, supplier, employee, order, stock, dashboard, settings, pos; hoặc theo tầng kỹ thuật khi không gắn với module cụ thể: controller, service, repository, dto, frontend, config.
Mỗi commit chỉ giải quyết một việc duy nhất, tránh gộp feat và fix không liên quan trong cùng một commit.
Dòng mô tả ngắn giới hạn khoảng 50-72 ký tự; nếu cần giải thích thêm, xuống dòng trống rồi viết phần mô tả chi tiết.
Breaking change: thêm ! sau type/scope và ghi rõ trong footer, ví dụ feat(product)!: đổi format response API sang chuẩn PageResponse mới kèm BREAKING CHANGE: ... ở footer.
Commit liên quan đến business rule trong SRS nên trích dẫn mã đó ở phần mô tả chi tiết hoặc footer, ví dụ Refs: BR-10.