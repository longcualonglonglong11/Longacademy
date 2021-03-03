import Skeleton from "@yisheng90/react-loading";
function SkeletonLoader(props) {
  let { width, num } = props;

  width *= 19 / 20;
  const Skeletons = ({ num }) => {
    if (!num) num = 4;
    let skeletons = [];

    for (let i = 0; i < num; i++) {
      skeletons.push(<Skeleton width={width} />);
    }
    return <>{skeletons}</>;
  };
  return (
    <div className="SkeletonLoader">
      <Skeleton class="skeleton" circle width={45} />

      <Skeletons num={num} />

      <Skeleton class="skeleton-resize-width" width={(width * 3) / 4} />
    </div>
  );
}
export default SkeletonLoader;
