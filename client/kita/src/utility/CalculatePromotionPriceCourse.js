export default function (price, discount) {
  return (price - (price * discount) / 100).toFixed(2);
}
